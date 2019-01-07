package com.centersoft.dao.impl;

import com.centersoft.dao.AddMessageDao;
import com.centersoft.entity.AddMessage;
import com.centersoft.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
@Component
public class AddMessageDaoImpl<T extends BaseEntity> extends BaseDaoImpl<AddMessage> implements AddMessageDao<AddMessage> {

    /**
     * @description 查询消息盒子信息
     */
    @Override
    public List<AddMessage> findAddInfo(String userId) {
        List<AddMessage> list = findByColumn("toUid",userId);
        return list;
    }


}
