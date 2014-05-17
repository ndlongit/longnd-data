package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mhostshis;

import static jp.co.inte.crm.common.entity.names.MhostshisNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mhostshis}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MhostshisBaseDao extends CrmBaseDao<Mhostshis> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param hoid
     *            識別子
     * @param historyid
     *            識別子
     * @return エンティティ
     */
    public Mhostshis findById(String hoid, BigDecimal historyid) {
        return select().id(hoid, historyid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mhostshis> findAllOrderById() {
        return select().orderBy(asc(hoid()), asc(historyid())).getResultList();
    }
}
