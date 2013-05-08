// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.ContentTypeDao;
import vn.pyco.tinycms.model.ContentType;
import vn.pyco.tinycms.services.ContentTypeService;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Service(ContentTypeService.SERVICE_ID)
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ContentTypeServiceImpl implements ContentTypeService {
    @Autowired
    private ContentTypeDao _contentTypeDao;

    @Transactional
    public void saveContentType(ContentType contentType) {
        _contentTypeDao.save(contentType);
    }
    @Transactional
    public List<ContentType> findAll() {
        return _contentTypeDao.getAll();
    }

    @Override
    @Transactional
    public ContentType getObjectByCriteria(String name, String value) {
        String[] names = new String[1];
        names[0] = name;

        String[] values = new String[1];
        values[0] = value;
        return this.getObjectByCriteria(names, values);
    }

    @Override
    @Transactional
    public ContentType getObjectByCriteria(String[] names, String[] values) {
        return _contentTypeDao.getObjectByCriteria(names, values);
    }
}
