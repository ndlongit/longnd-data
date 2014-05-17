package jp.co.inte.crm.common.dao;

import static jp.co.inte.crm.common.entity.names.TslmtrlBaseNames.slmtrlid;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import javax.annotation.Generated;

import jp.co.inte.crm.common.entity.Tslmtrl;

/**
 * {@link Tslmtrl}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
        date = "2014/05/13 17:07:24")
public class TslmtrlBaseDao extends CrmBaseDao<Tslmtrl> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param slmtrlid
     *            識別子
     * @return エンティティ
     */
    public Tslmtrl findById(String slmtrlid) {
        return select().id(slmtrlid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tslmtrl> findAllOrderById() {
        return select().orderBy(asc(slmtrlid())).getResultList();
    }

    /**
     * Delete Sale Material
     * 
     * @param tslmtrl
     * 
     * @return integer
     */
    public int deleteSaleMaterial(Tslmtrl tslmtrl) {
        return delete(tslmtrl);
    }
}
