package jp.co.inte.crm.common.dao;

import javax.annotation.Generated;

import jp.co.inte.crm.common.entity.Mmtrlflgxdept;

/**
 * {@link Mmtrlflgxdept}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
        date = "2014/05/13 17:07:24")
public class MmtrlflgxdeptBaseDao extends CrmBaseDao<Mmtrlflgxdept> {

    public Mmtrlflgxdept findFisrt() {
        return select().getSingleResult();
    }
}
