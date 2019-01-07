package com.centersoft.dao;

import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.UserEntity;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
public interface UserDao<T extends BaseEntity> extends BaseDao<UserEntity> {

    //根据名称查询 user
    UserEntity findUserByUserName(String name);

    //根据Token查询 user
    UserEntity findUserByToken(String access_token);


    /**
     * 根据用户名查询 对应的人员
     *
     * @return
     */
    List<UserEntity> findUsersByName(String page, String name);

    UserEntity findByEmpid(String findByEmpid);
}
