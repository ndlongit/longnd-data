package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Magt;

import static jp.co.inte.crm.common.entity.names.MagtNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Magt}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MagtBaseDao extends CrmBaseDao<Magt> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param agtcd
     *            識別子
     * @return エンティティ
     */
    public Magt findById(String agtcd) {
        return select().id(agtcd).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Magt> findAllOrderById() {
        return select().orderBy(asc(agtcd())).getResultList();
    }
}
