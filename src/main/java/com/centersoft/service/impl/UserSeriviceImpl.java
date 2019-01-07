package com.centersoft.service.impl;

import com.centersoft.dao.UserDao;
import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.Employee;
import com.centersoft.entity.SUser;
import com.centersoft.entity.UserEntity;
import com.centersoft.exception.RepeatException;
import com.centersoft.service.UserSerivice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by liudong on 2018/6/8.
 */
@Service
public class UserSeriviceImpl<T extends BaseEntity> extends BaseSeriviceImpl<UserEntity> implements UserSerivice<UserEntity> {

    @Resource
    UserDao userDao;


    @Override
    @Transactional
    public void delUser(String empid) {
        UserEntity entity = userDao.findByEmpid(empid);
        if(entity != null){
            delEntity(entity);
        }
    }

    @Override
    public UserEntity findUserByUserName(String name) {
        return userDao.findUserByUserName(name);
    }

    @Override
    public UserEntity findUserByToken(String access_token) {
        return userDao.findUserByToken(access_token);
    }

    @Override
    public UserEntity findByEmpid(String findByEmpid) {
        return userDao.findByEmpid(findByEmpid);
    }

    /**
     * 注册
     *
     * @param name
     * @param password
     * @return
     */
    @Override
    @Transactional
    public UserEntity register(String name, String password, String avatar) {

        //用户名必须唯一
        if (userDao.findUserByUserName(name) != null) {
            throw new RepeatException(-1, "用户名不能重复");
        }

        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        //获取三十天后日期
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(theCa.DATE, 30);
        Date start = theCa.getTime();
        String startDate = sdf.format(start);//三十天之后日期

        UserEntity user = new UserEntity();
        user.setAuth_token(UUID.randomUUID().toString());
        user.setAuth_date(startDate);
        user.setUsername(name);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setSign("");
        saveEntity(user);
        return user;
    }

    /**
     * 注册
     *
     * @return
     */
    @Override
    @Transactional
    public UserEntity register(SUser S_User, Employee employee) {

        if (StringUtils.isEmpty(S_User.getEmployeeid())) {
            throw new RepeatException(-1, "员工id不能为空");
        }
        //用户名必须唯一
        if (userDao.findByEmpid(S_User.getEmployeeid()) != null) {
            throw new RepeatException(-1, "同一员工不能重复注册");
        }

        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        //获取三十天后日期
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(theCa.DATE, 30);
        Date start = theCa.getTime();
        String startDate = sdf.format(start);//三十天之后日期

        UserEntity user = new UserEntity();
        user.setAuth_token(UUID.randomUUID().toString());
        user.setAuth_date(startDate);
        user.setUsername(employee.getPsnname());
        user.setPassword(S_User.getPassword());
        user.setAvatar(employee.getImgUrl());
        user.setEmpid(employee.getId());
        user.setNickname(S_User.getLoginname());
        saveEntity(user);
        return user;
    }

    /**
     * 根据用户名查询 对应的人员
     *
     * @return
     */
    @Override
    public List<UserEntity> findUsersByName(String page, String name) {
        return userDao.findUsersByName(page, name);
    }

    @Override
    public List findAll(String sql) {
        return userDao.getListResult(sql, null);
    }
}
