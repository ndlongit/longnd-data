package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tclaim;

import static jp.co.inte.crm.common.entity.names.TclaimNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tclaim}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TclaimBaseDao extends CrmBaseDao<Tclaim> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param claimid
     *            識別子
     * @return エンティティ
     */
    public Tclaim findById(String claimid) {
        return select().id(claimid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tclaim> findAllOrderById() {
        return select().orderBy(asc(claimid())).getResultList();
    }
}
