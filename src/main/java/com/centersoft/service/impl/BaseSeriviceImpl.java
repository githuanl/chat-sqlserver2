package com.centersoft.service.impl;

import com.centersoft.dao.BaseDao;
import com.centersoft.entity.BaseEntity;
import com.centersoft.service.BaseSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */
public class BaseSeriviceImpl<T extends BaseEntity> implements BaseSerivice<T> {

    @Qualifier("userDaoImpl")
    @Autowired
    BaseDao<T> baseDao;

    protected Class<T> entityClass;

    public BaseSeriviceImpl() {
        // 使用反射技术得到T的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
        this.entityClass = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
    }

    @Override
    public T getEntityById(String id) {
        return baseDao.findUniqueByColumn("id", id);
    }


    @Transactional
    @Override
    public void saveEntity(T entity) {
        baseDao.saveEntity(entity);
    }

    @Transactional
    @Override
    public T mergeEntity(T entity) {
        return baseDao.mergeEntity(entity);
    }

    @Transactional
    @Override
    public void delEntity(T entity) {
        baseDao.delEntity(entity);
    }


    @Override
    public List<T> selectAll() {
        return baseDao.findAll();
    }
}
