package jp.co.inte.crm.common.dao;

import jp.co.inte.crm.common.entity.CrmBaseEntity;
import jp.co.inte.cspfw.dao.AbstractFwJdbcDao;

/**
 * 
 * CRM-System BaseDao Class.
 * 
 * @param <ENTITY> エンティティクラス.
 */
public class CrmBaseDao<ENTITY extends CrmBaseEntity> extends AbstractFwJdbcDao<ENTITY> {

}
