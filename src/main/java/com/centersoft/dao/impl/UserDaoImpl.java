package com.centersoft.dao.impl;

import com.centersoft.dao.UserDao;
import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class UserDaoImpl<T extends BaseEntity> extends BaseDaoImpl<UserEntity> implements UserDao<UserEntity> {

    /**
     * 根据用户名查询对象
     *
     * @param name
     * @return
     */
    @Override
    public UserEntity findUserByUserName(String name) {
        return findUniqueByColumn("username", name);
    }

    /**
     * 根据用户Token查询对象
     *
     * @param access_token
     * @return
     */
    @Override
    public UserEntity findUserByToken(String access_token) {
        return findUniqueByColumn("auth_token", access_token);
    }

    /**
     * 根据用户名查询 对应的人员
     *
     * @return
     */
    @Override
    public List<UserEntity> findUsersByName(String page, String name) {
        List<UserEntity> list;
        if (!StringUtils.isEmpty(name)) {
            list = findByColumn("username", name);
        } else {
            list = findAll();
        }
        return list;
    }

    @Override
    public UserEntity findByEmpid(String empid) {
        return findUniqueByColumn("empid", empid);
    }
}
