package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltstshis;

import static jp.co.inte.crm.common.entity.names.McltstshisNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mcltstshis}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class McltstshisBaseDao extends CrmBaseDao<Mcltstshis> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param cltid
     *            識別子
     * @param historyid
     *            識別子
     * @return エンティティ
     */
    public Mcltstshis findById(String cltid, BigDecimal historyid) {
        return select().id(cltid, historyid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mcltstshis> findAllOrderById() {
        return select().orderBy(asc(cltid()), asc(historyid())).getResultList();
    }
}
