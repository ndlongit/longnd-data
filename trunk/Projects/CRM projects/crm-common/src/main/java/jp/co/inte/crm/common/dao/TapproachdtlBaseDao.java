package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tapproachdtl;

import static jp.co.inte.crm.common.entity.names.TapproachdtlNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tapproachdtl}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TapproachdtlBaseDao extends CrmBaseDao<Tapproachdtl> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param approachid
     *            識別子
     * @return エンティティ
     */
    public Tapproachdtl findById(String approachid) {
        return select().id(approachid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tapproachdtl> findAllOrderById() {
        return select().orderBy(asc(approachid())).getResultList();
    }
}
