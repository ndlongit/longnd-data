package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Tcomcompeslttl;

import static jp.co.inte.crm.common.entity.names.TcomcompeslttlNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Tcomcompeslttl}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TcomcompeslttlBaseDao extends CrmBaseDao<Tcomcompeslttl> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param cltid
     *            識別子
     * @return エンティティ
     */
    public Tcomcompeslttl findById(String cltid) {
        return select().id(cltid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Tcomcompeslttl> findAllOrderById() {
        return select().orderBy(asc(cltid())).getResultList();
    }
}
