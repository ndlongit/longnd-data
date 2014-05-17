package jp.co.inte.crm.common.dao;

import java.sql.Date;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mcalendar;

import static jp.co.inte.crm.common.entity.names.McalendarNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mcalendar}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class McalendarBaseDao extends CrmBaseDao<Mcalendar> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param date
     *            識別子
     * @return エンティティ
     */
    public Mcalendar findById(Date date) {
        return select().id(date).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mcalendar> findAllOrderById() {
        return select().orderBy(asc(date())).getResultList();
    }
}
