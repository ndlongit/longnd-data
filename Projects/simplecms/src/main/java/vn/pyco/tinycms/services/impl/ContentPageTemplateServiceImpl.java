// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.ContentPageTemplateDao;
import vn.pyco.tinycms.model.ContentPageTemplate;
import vn.pyco.tinycms.services.ContentPageTemplateService;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Service(ContentPageTemplateService.SERVICE_ID)
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ContentPageTemplateServiceImpl implements ContentPageTemplateService {

    @Autowired
    private ContentPageTemplateDao _contentPageTemplateDao;

    @Transactional
    @Override
    public void save(ContentPageTemplate template) {
        _contentPageTemplateDao.save(template);
    }
}
