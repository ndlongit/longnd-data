package jp.co.inte.crm.common.dao;

import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Ttarget;

import static jp.co.inte.crm.common.entity.names.TtargetNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Ttarget}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class TtargetBaseDao extends CrmBaseDao<Ttarget> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param inteempno
     *            識別子
     * @param date
     *            識別子
     * @param ttlunit
     *            識別子
     * @param productkbn
     *            識別子
     * @return エンティティ
     */
    public Ttarget findById(String inteempno, String date, Short ttlunit, Short productkbn) {
        return select().id(inteempno, date, ttlunit, productkbn).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Ttarget> findAllOrderById() {
        return select().orderBy(asc(inteempno()), asc(date()), asc(ttlunit()), asc(productkbn())).getResultList();
    }
}
