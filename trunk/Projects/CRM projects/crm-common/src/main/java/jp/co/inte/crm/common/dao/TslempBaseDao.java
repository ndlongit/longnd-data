package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tslemp;

import static jp.co.inte.crm.common.entity.names.TslempNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tslemp}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TslempBaseDao extends CrmBaseDao<Tslemp> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param ordcd
     *            識別子
     * @param itemcd
     *            識別子
     * @return エンティティ
     */
    public Tslemp findById(Integer ordcd, Integer itemcd) {
        return select().id(ordcd, itemcd).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tslemp> findAllOrderById() {
        return select().orderBy(asc(ordcd()), asc(itemcd())).getResultList();
    }
}
