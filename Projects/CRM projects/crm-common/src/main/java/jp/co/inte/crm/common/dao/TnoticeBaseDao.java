package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tnotice;

import static jp.co.inte.crm.common.entity.names.TnoticeNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tnotice}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TnoticeBaseDao extends CrmBaseDao<Tnotice> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param tnoticeid
     *            識別子
     * @return エンティティ
     */
    public Tnotice findById(BigDecimal tnoticeid) {
        return select().id(tnoticeid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tnotice> findAllOrderById() {
        return select().orderBy(asc(tnoticeid())).getResultList();
    }
}
