package com.centersoft.dao;

import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.GroupEntity;
import com.centersoft.entity.GroupUser;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
public interface GroupDao<T extends BaseEntity> extends BaseDao<GroupEntity> {

    /**
     * 根据群的名字查询所有的群
     *
     * @return
     */
    List<GroupEntity> findGroupsByGroupName(String groupName);


    /**
     * 获取 我所在的 所有的群
     *
     * @return
     */
    List<GroupEntity> findMyGroupsByUserId(String userId);



    /**
     * 获取 群 下面的所有人员
     * @return
     */
    List<GroupUser> findUsersByGroupId(String group_id);



}
