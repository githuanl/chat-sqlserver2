package com.centersoft.dao.impl;

import com.centersoft.dao.BaseDao;
import com.centersoft.entity.BaseEntity;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by summer on 2017/5/5.
 */
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @Resource
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public BaseDaoImpl() {
        // 使用反射技术得到T的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
        this.entityClass = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
    }

    public void saveEntity(T entity) {
        entityManager.persist(entity);
    }

    public T mergeEntity(T entity) {
        return entityManager.merge(entity);
    }

    public void delEntity(T entity) {
        entityManager.remove(entity);
    }

    /**
     * 执行查询语句，返回唯一对象
     *
     * @param qlStr 查询语句
     * @return
     */
    @Override
    public Object getUniqueResult(String qlStr) {
        Query query = entityManager.createNativeQuery(qlStr);
        return query.getSingleResult();
    }

    @Override
    public List getListResult(String qlStr, Class cla) {
        Query query = cla != null ? entityManager.createNativeQuery(qlStr, cla) : entityManager.createNativeQuery(qlStr);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    /**
     * 执行传入的语句  (修改  删除)
     *
     * @param qlStr
     * @return
     */
    @Override
    public int executeUpdate(String qlStr) {
        Query query = entityManager.createQuery(qlStr);
        return query.executeUpdate();
    }

    @Override
    public int executeUpdateNative(String qlStr) {
        Query query = entityManager.createNativeQuery(qlStr);
        return query.executeUpdate();
    }


    @Override
    public Map<String, Object> getUniqueMapForSql(String sql, Map<String, Object> values) {
        Query query = entityManager.createNativeQuery(sql);
        if (values != null && !values.isEmpty()) {
            Set<String> keySet = values.keySet();
            for (String key : keySet) {
                query.setParameter(key, values.get(key));
            }
        }
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> list = query.getResultList();
        Map<String, Object> reMap = null;
        if (list.size() > 0) {
            reMap = list.get(0);
        }
        return reMap;
    }

    /**
     * 执行传入的原生sql语句 返回对应的Map类型的List结果
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<Map> listMapForSql(String sql, Map<String, Object> values) {
        Query query = entityManager.createNativeQuery(sql);
        setParams(query, values);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    @Override
    public List<Map> listMapForSql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    protected void setParams(Query query, Map<String, Object> values) {
        if (values != null && !values.isEmpty()) {
            int v_size = values.size();
            Iterator<String> mapKey = values.keySet().iterator();
            for (int i = 0; i < v_size; i++) {
                String key = mapKey.next();
                Object value = values.get(key);
                try {
                    if (value != null && value instanceof java.util.List) {
                        query.setParameter(key, value);

                    } else if (value != null && value.getClass().isArray()) {
                        query.setParameter(key, value);
                    } else {
                        if (value != null) {
                            query.setParameter(key, value);
                        } else {
                            query.setParameter(key, "");
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
    }


    public Object findUniqueByColumn(String column, String colunmVable, Class cla) {

        List list = findByColumn(column, colunmVable, cla);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public T findUniqueByColumn(String column, String colunmVable) {
        return (T) findUniqueByColumn(column, colunmVable, entityClass);
    }


    @Override
    public List<T> findByColumn(String column, String colunmVable) {
        return findByColumn(column, colunmVable, entityClass);
    }

    @Override
    public List findByColumn(String column, String colunmVable, Class cla) {
        if (!StringUtils.isEmpty(column) && !StringUtils.isEmpty(colunmVable)) {
            StringBuffer sb = new StringBuffer("select t from " + cla.getSimpleName() + " t ");
            sb.append(" where t." + column + " = ?1");
            Query query = entityManager.createQuery(sb.toString(), cla);
            query.setParameter(1, colunmVable);
            return query.getResultList();
        } else {
            return findAll(cla);
        }
    }

    @Override
    public List<T> findAll() {
        return findAll(entityClass);
    }

    @Override
    public List findAll(Class cla) {
        return entityManager.createQuery("select e from " + entityClass.getSimpleName() + " e", cla).getResultList();
    }


}
