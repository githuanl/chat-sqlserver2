package com.centersoft.controller;

import com.alibaba.fastjson.JSON;
import com.centersoft.constant.CookieConstant;
import com.centersoft.constant.RedisConstant;
import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.Result;
import com.centersoft.entity.UserEntity;
import com.centersoft.enums.EResultType;
import com.centersoft.service.UserSerivice;
import com.centersoft.utils.CookieUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class BaseController<T extends BaseEntity> {


    @Resource
    HttpServletRequest loginReq;

    @Resource
    UserSerivice serivice;

    @Autowired
    protected StringRedisTemplate redisTemplate;


    protected UserEntity getCurrentUser() {
        Cookie cookie = CookieUtil.get(loginReq, CookieConstant.TOKEN);
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        UserEntity userEntity = serivice.findByEmpid(tokenValue);
        userEntity.setPassword("");
        return userEntity;
    }

    protected Logger logger = LogManager.getLogger(getClass().getName());

    protected String retResultData(EResultType type) {
        return JSON.toJSONString(new Result(type));
    }

    protected String retResultData(EResultType type, Object data) {
        return JSON.toJSONString(new Result(type, data));
    }

    protected String retResultData(Integer code, String message) {
        return JSON.toJSONString(new Result(code, message));
    }

    protected String retResultData(Integer code, String message, Object data) {
        return JSON.toJSONString(new Result(code, message, data));
    }

}
