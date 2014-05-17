package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mintrausrsec;

import static jp.co.inte.crm.common.entity.names.MintrausrsecNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mintrausrsec}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MintrausrsecBaseDao extends CrmBaseDao<Mintrausrsec> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param inteempno
     *            識別子
     * @param deptsecno
     *            識別子
     * @return エンティティ
     */
    public Mintrausrsec findById(String inteempno, BigDecimal deptsecno) {
        return select().id(inteempno, deptsecno).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mintrausrsec> findAllOrderById() {
        return select().orderBy(asc(inteempno()), asc(deptsecno())).getResultList();
    }
}
