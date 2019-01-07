package com.centersoft.service;

import com.centersoft.entity.BaseEntity;

import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */

public interface BaseSerivice<T extends BaseEntity> {
    /**
     * 根据Id查询实体
     */
    T getEntityById(String id);

    /**
     * 新增实体
     */
    void saveEntity(final T entity);

    void delEntity(T entity);

    /**
     * 查询所有
     */
    List<T> selectAll();

    T mergeEntity(T entity);

}
