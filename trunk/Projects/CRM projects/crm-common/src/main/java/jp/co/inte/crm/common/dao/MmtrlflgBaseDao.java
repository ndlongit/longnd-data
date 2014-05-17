package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import jp.co.inte.crm.common.entity.Mmtrlflg;

/**
 * {@link Mmtrlflg}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
        date = "2014/05/13 17:07:24")
public class MmtrlflgBaseDao extends CrmBaseDao<Mmtrlflg> {

    public Mmtrlflg findById(BigDecimal flgno) {
        Map map = new HashMap<String, BigDecimal>();
        map.put("flgno", flgno);

        return select().where(map).getSingleResult();
    }
}
