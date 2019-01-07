package com.centersoft.service;

import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.Employee;
import com.centersoft.entity.SUser;
import com.centersoft.entity.UserEntity;

import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */

public interface UserSerivice<T extends BaseEntity> extends BaseSerivice<UserEntity> {


    /**
     * 根据名称查具体的人
     *
     * @param name
     * @return
     */
    UserEntity findUserByUserName(String name);

    /**
     * 根据名称查具体的人
     *
     * @param empid
     * @return
     */
    UserEntity findByEmpid(String empid);

    /**
     * 使用 token 查询 实体
     *
     * @param access_token
     * @return
     */
    UserEntity findUserByToken(String access_token);

    /**
     * 注册
     *
     * @param name
     * @param password
     * @return
     */
    UserEntity register(String name, String password, String avatar);

    UserEntity register(SUser sUser, Employee employee);

    /**
     * 根据用户名查询 对应的人员
     *
     * @return
     */
    List<UserEntity> findUsersByName(String page, String name);


    List findAll(String sql);

    void delUser(String empid);


}
