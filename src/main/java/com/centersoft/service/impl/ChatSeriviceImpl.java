package com.centersoft.service.impl;

import com.centersoft.entity.BaseEntity;
import com.centersoft.entity.MessageEntity;
import com.centersoft.service.ChatSerivice;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Created by liudong on 2018/6/8.
 */
@Component
public class ChatSeriviceImpl<T extends BaseEntity> extends BaseSeriviceImpl<MessageEntity> implements ChatSerivice<MessageEntity> {

    @Override
    @Transactional
    public void saveMessageData(MessageEntity entity) {
        long time = new Date().getTime();
        entity.setTimestamp(time);
        saveEntity(entity);
    }



    @Override
    public List findListMessage(String state, String to_user_id) {
        StringBuffer sql = new StringBuffer("select * from c_message where to_user_id ='" + to_user_id + "' and state = '" + state + "' ");
        return baseDao.listMapForSql(sql.toString());
    }
}
