package com.centersoft.service;

import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.MessageEntity;

import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */

public interface ChatSerivice<T extends BaseEntity> extends BaseSerivice<MessageEntity> {

    void saveMessageData(T entity);

    List findListMessage(String state,String to_user_id);
}
