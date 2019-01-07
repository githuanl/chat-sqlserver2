package com.centersoft.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.centersoft.dao.AddMessageDao;
import com.centersoft.dao.GroupDao;
import com.centersoft.dao.UserDao;
import com.centersoft.entity.*;
import com.centersoft.enums.AddMessageType;
import com.centersoft.exception.RepeatException;
import com.centersoft.service.AddMessageSerivice;
import com.centersoft.service.GroupSerivice;
import com.centersoft.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */
@Component
public class AddMessageSeriviceImpl<T extends BaseEntity> extends BaseSeriviceImpl<UserEntity> implements AddMessageSerivice<UserEntity> {

    @Resource
    UserDao userDao;

    @Resource
    AddMessageDao addMessageDao;

    @Resource
    GroupDao groupDao;

    @Resource
    GroupSerivice groupSerivice;

    /**
     * 添加好友、群组信息请求
     *
     * @param msg
     */
    @Override
    @Transactional
    public void saveAddMessage(AddMessage msg) {
        msg.setTime(DateUtils.getDataTimeYMDHMS());
        userDao.saveEntity(msg);
    }

    /**
     * @description 查询消息盒子信息
     */
    @Override
    public JSONObject findAddInfo(String userId) {

        JSONObject obj = new JSONObject();

        List<AddMessage> list = addMessageDao.findAddInfo(userId);

        List<AddInfo> infos = new ArrayList<>();
        for (AddMessage message : list) {
            AddInfo info = new AddInfo();
            if (message.getType().equals("group")) {
                GroupEntity entity = (GroupEntity) groupDao.findUniqueByColumn("id", message.getGroupId(), GroupEntity.class);
                info.setContent("申请加入 '" + entity.getGroupname() + "' 群聊!");
            } else {
                info.setContent("申请添加你为好友");
            }

            info.setId(message.getId());
            info.setType(message.getType());
            info.setFrom(message.getFromUid());
            info.setUid(message.getToUid());
            info.setRead(message.getMsgResult().toString());
            UserEntity userEntity = getEntityById(message.getFromUid());
            userEntity.setPassword("");
            userEntity.setAuth_token("");
            info.setUser(userEntity);
            info.setFrom_group(message.getGroupId());
            info.setTime(message.getTime());
            info.setRemark(message.getRemark());
            infos.add(info);
        }

        obj.put("list", infos);
        obj.put("pages", 1);
        return obj;
    }


    /**
     * 更新 添加消息数据
     *
     * @param entity
     * @param messageId
     * @return
     */
    @Override
    @Transactional
    public void updateAddMessage(UserEntity entity, String groupId, String messageId) {

        String userId = entity.getId(); //申请加群的人
        //查询群下面的所有人  如果有当前群包含了 自己则说明是重复加群
        List<GroupUser> groupUsers = groupSerivice.findUsersByGroupId(groupId);
        for (GroupUser user : groupUsers) {
            if (user.getUser_id().equals(userId)) {
                throw new RepeatException(-1, "不能重复加群");
            }
        }

        AddMessage addMessage = (AddMessage) addMessageDao.findUniqueByColumn("id", messageId);
        addMessage.setMsgResult(AddMessageType.Agree);
        addMessageDao.saveEntity(addMessage);
        groupSerivice.joinGroup(entity, groupId);
    }

    /**
     * 拒绝添加群组，或者，好友
     *
     * @param messageBoxId
     */
    @Override
    public void updateAddMessage(String messageBoxId) {
        AddMessage addMessage = (AddMessage) addMessageDao.findUniqueByColumn("id", messageBoxId);
        addMessage.setMsgResult(AddMessageType.Reject);
        addMessageDao.saveEntity(addMessage);  //更新数据
    }


}
