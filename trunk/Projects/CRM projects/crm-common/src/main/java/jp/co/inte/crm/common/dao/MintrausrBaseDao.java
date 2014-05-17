package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mintrausr;

import static jp.co.inte.crm.common.entity.names.MintrausrNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mintrausr}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MintrausrBaseDao extends CrmBaseDao<Mintrausr> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param inteempno
     *            識別子
     * @return エンティティ
     */
    public Mintrausr findById(String inteempno) {
        return select().id(inteempno).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mintrausr> findAllOrderById() {
        return select().orderBy(asc(inteempno())).getResultList();
    }
}
