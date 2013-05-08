// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.MasterPageTemplateDao;
import vn.pyco.tinycms.model.MasterPageTemplate;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Repository(MasterPageTemplateDao.SERVICE_ID)
public class MasterPageTemplateDaoImpl extends GenericDaoImpl<MasterPageTemplate, Integer> implements
                                MasterPageTemplateDao {
}
