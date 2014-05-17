package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltxhocg;

import static jp.co.inte.crm.common.entity.names.McltxhocgNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mcltxhocg}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class McltxhocgBaseDao extends CrmBaseDao<Mcltxhocg> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param cghocltid
     *            識別子
     * @return エンティティ
     */
    public Mcltxhocg findById(String cghocltid) {
        return select().id(cghocltid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mcltxhocg> findAllOrderById() {
        return select().orderBy(asc(cghocltid())).getResultList();
    }
}
