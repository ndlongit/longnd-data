// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.ContentPageTemplateDao;
import vn.pyco.tinycms.model.ContentPageTemplate;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Repository(ContentPageTemplateDao.SERVICE_ID)
public class ContentPageTemplateDaoImpl extends GenericDaoImpl<ContentPageTemplate, Integer> implements
                                ContentPageTemplateDao {
}
