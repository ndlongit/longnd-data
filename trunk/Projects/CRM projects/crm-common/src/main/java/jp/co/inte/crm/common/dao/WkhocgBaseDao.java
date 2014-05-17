package jp.co.inte.crm.common.dao;

import static jp.co.inte.crm.common.entity.names.WkhocgBaseNames.importno;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import javax.annotation.Generated;

import jp.co.inte.crm.common.entity.WkhocgEntity;

/**
 * {@link WkhocgEntity}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
        date = "2014/05/15 21:29:00")
public class WkhocgBaseDao extends CrmBaseDao<WkhocgEntity> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param importno
     *            識別子
     * @return エンティティ
     */
    public WkhocgEntity findById(Integer importno) {
        return select().id(importno).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<WkhocgEntity> findAllOrderById() {
        return select().orderBy(asc(importno())).getResultList();
    }
}
