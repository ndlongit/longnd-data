package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tapproachplanhis;

import static jp.co.inte.crm.common.entity.names.TapproachplanhisNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tapproachplanhis}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TapproachplanhisBaseDao extends CrmBaseDao<Tapproachplanhis> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param approachid
     *            識別子
     * @param historyid
     *            識別子
     * @return エンティティ
     */
    public Tapproachplanhis findById(String approachid, BigDecimal historyid) {
        return select().id(approachid, historyid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tapproachplanhis> findAllOrderById() {
        return select().orderBy(asc(approachid()), asc(historyid())).getResultList();
    }
}
