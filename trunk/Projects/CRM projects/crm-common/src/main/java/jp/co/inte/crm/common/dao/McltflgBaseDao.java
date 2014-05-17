package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltflg;

import static jp.co.inte.crm.common.entity.names.McltflgNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mcltflg}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class McltflgBaseDao extends CrmBaseDao<Mcltflg> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param flgno
     *            識別子
     * @return エンティティ
     */
    public Mcltflg findById(BigDecimal flgno) {
        return select().id(flgno).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mcltflg> findAllOrderById() {
        return select().orderBy(asc(flgno())).getResultList();
    }
}
