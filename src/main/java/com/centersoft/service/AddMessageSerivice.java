package com.centersoft.service;

import com.alibaba.fastjson.JSONObject;
import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.UserEntity;
import com.centersoft.entity.AddMessage;

/**
 * Created by liudong on 2018/6/8.
 */

public interface AddMessageSerivice<T extends BaseEntity> extends BaseSerivice<UserEntity> {

    /**
     * 添加好友、群组信息请求
     * @param msg
     */
    void saveAddMessage(AddMessage msg);

    /**
     * @description 查询消息盒子信息
     */
    JSONObject findAddInfo(String userId);


    /**
     * 拒绝添加群组，或者，好友
     * @param messageBoxId
     */
    void updateAddMessage(String messageBoxId);


    /**
     * 更新 添加消息数据
     * @param entity
     * @param messageId
     * @return
     */
    void updateAddMessage(UserEntity entity,String groupId,String messageId);

}
