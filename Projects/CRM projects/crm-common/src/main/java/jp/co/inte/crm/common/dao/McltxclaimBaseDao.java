package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltxclaim;

import static jp.co.inte.crm.common.entity.names.McltxclaimNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mcltxclaim}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class McltxclaimBaseDao extends CrmBaseDao<Mcltxclaim> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param claimid
     *            識別子
     * @param cltid
     *            識別子
     * @return エンティティ
     */
    public Mcltxclaim findById(String claimid, String cltid) {
        return select().id(claimid, cltid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mcltxclaim> findAllOrderById() {
        return select().orderBy(asc(claimid()), asc(cltid())).getResultList();
    }
}
