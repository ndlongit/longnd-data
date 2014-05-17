package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcltrank;

import static jp.co.inte.crm.common.entity.names.McltrankNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mcltrank}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class McltrankBaseDao extends CrmBaseDao<Mcltrank> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param rankno
     *            識別子
     * @return エンティティ
     */
    public Mcltrank findById(Short rankno) {
        return select().id(rankno).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mcltrank> findAllOrderById() {
        return select().orderBy(asc(rankno())).getResultList();
    }
}
