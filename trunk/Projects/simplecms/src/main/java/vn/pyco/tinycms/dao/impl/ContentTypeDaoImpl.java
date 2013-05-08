// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao.impl;

import org.springframework.stereotype.Repository;

import vn.pyco.commons.dao.impl.GenericDaoImpl;
import vn.pyco.tinycms.dao.ContentTypeDao;
import vn.pyco.tinycms.model.ContentType;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Repository(ContentTypeDao.SERVICE_ID)
public class ContentTypeDaoImpl extends GenericDaoImpl<ContentType, Integer> implements ContentTypeDao {
}
