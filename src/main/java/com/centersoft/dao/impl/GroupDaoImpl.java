package com.centersoft.dao.impl;

import com.centersoft.dao.GroupDao;
import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.GroupEntity;
import com.centersoft.entity.GroupUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class GroupDaoImpl<T extends BaseEntity> extends BaseDaoImpl<GroupEntity> implements GroupDao<GroupEntity> {


    /**
     * 根据群的名字 查询所有的群
     *
     * @return
     */
    @Override
    public List<GroupEntity> findGroupsByGroupName(String groupName) {
        if (StringUtils.isEmpty(groupName)) {
            return findAll();
        }
        return (List<GroupEntity>) getListResult("select * from  c_group  where groupname like '%" + groupName + "%'", entityClass);
    }

    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    @Override
    public List<GroupEntity> findMyGroupsByUserId(String user_id) {
        // 我创建的群
        List<GroupEntity> list = findByColumn("user_id", user_id);

        //我加过的群
        List<GroupUser> users = findByColumn("user_id", user_id, GroupUser.class);
        for (GroupUser user : users) {
            GroupEntity entity = findUniqueByColumn("id", user.getGroup_id());
            if (entity != null && !entity.getUser_id().equals(user_id)) { //不是自己创建的群 (只是加入的群)
                list.add(entity);
            }
        }
        return list;
    }


    /**
     * 根据群id 查询群下面的所有的人
     *
     * @return
     */
    @Override
    public List<GroupUser> findUsersByGroupId(String group_id) {
        return findByColumn("group_id", group_id, GroupUser.class);
    }


}
