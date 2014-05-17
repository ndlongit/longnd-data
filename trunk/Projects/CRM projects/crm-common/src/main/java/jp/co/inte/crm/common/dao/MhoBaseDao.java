package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mho;

import static jp.co.inte.crm.common.entity.names.MhoNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mho}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MhoBaseDao extends CrmBaseDao<Mho> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param hoid
     *            識別子
     * @return エンティティ
     */
    public Mho findById(String hoid) {
        return select().id(hoid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mho> findAllOrderById() {
        return select().orderBy(asc(hoid())).getResultList();
    }
}
