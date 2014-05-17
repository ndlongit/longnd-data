package jp.co.inte.crm.common.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import jp.co.inte.crm.common.entity.Mactsum;

import static jp.co.inte.crm.common.entity.names.MactsumNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import jp.co.inte.crm.common.dao.CrmBaseDao;

/**
 * {@link Mactsum}のDaoクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl" },
           date = "2014/05/13 17:07:24")
public class MactsumBaseDao extends CrmBaseDao<Mactsum> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param ctrldeptno
     *            識別子
     * @param year
     *            識別子
     * @param quarter
     *            識別子
     * @return エンティティ
     */
    public Mactsum findById(String ctrldeptno, String year, BigDecimal quarter) {
        return select().id(ctrldeptno, year, quarter).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Mactsum> findAllOrderById() {
        return select().orderBy(asc(ctrldeptno()), asc(year()), asc(quarter())).getResultList();
    }
}
