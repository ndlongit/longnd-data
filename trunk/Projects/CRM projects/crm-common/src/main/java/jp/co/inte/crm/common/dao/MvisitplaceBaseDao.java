package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mvisitplace;

import static jp.co.inte.crm.common.entity.names.MvisitplaceNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mvisitplace}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MvisitplaceBaseDao extends CrmBaseDao<Mvisitplace> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param visitplaceid
     *            識別子
     * @return エンティティ
     */
    public Mvisitplace findById(String visitplaceid) {
        return select().id(visitplaceid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mvisitplace> findAllOrderById() {
        return select().orderBy(asc(visitplaceid())).getResultList();
    }
}
