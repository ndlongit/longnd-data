package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mclttransmng;

import static jp.co.inte.crm.common.entity.names.MclttransmngNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mclttransmng}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MclttransmngBaseDao extends CrmBaseDao<Mclttransmng> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param transid
     *            識別子
     * @return エンティティ
     */
    public Mclttransmng findById(String transid) {
        return select().id(transid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mclttransmng> findAllOrderById() {
        return select().orderBy(asc(transid())).getResultList();
    }
}
