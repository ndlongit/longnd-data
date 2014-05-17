package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tactplan;

import static jp.co.inte.crm.common.entity.names.TactplanNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tactplan}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TactplanBaseDao extends CrmBaseDao<Tactplan> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param tactplanid
     *            識別子
     * @return エンティティ
     */
    public Tactplan findById(String tactplanid) {
        return select().id(tactplanid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tactplan> findAllOrderById() {
        return select().orderBy(asc(tactplanid())).getResultList();
    }
}
