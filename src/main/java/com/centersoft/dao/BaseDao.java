package com.centersoft.dao;

import com.centersoft.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by summer on 2017/5/5.
 */
public interface BaseDao<T extends BaseEntity> {


    /**
     * 执行传入的原生sql语句 返回对应的Map类型的List结果
     *
     * @param sql
     * @param values
     * @return
     */
    List<Map> listMapForSql(String sql, Map<String, Object> values);

    List<Map> listMapForSql(String sql);


    public Map<String, Object> getUniqueMapForSql(String sql, Map<String, Object> values);

    /**
     * 执行查询语句，返回唯一对象
     *
     * @param qlStr 查询语句
     * @return
     */
    public Object getUniqueResult(String qlStr);

    List getListResult(String qlStr, Class cla);

    /**
     * 执行传入的语句  (修改  删除)
     *
     * @param qlStr
     * @return
     */
    public int executeUpdate(String qlStr);

    public int executeUpdateNative(String qlStr);

    T findUniqueByColumn(String column, String colunmVable);

    Object findUniqueByColumn(String column, String colunmVable, Class cla);

    List findByColumn(String column, String colunmVable, Class cla);

    List<T> findByColumn(String column, String colunmVable);

    List<T> findAll();

    List findAll(Class cla);

    void saveEntity(T t);

    void delEntity(T entity);

    T mergeEntity(T entity);

}
