// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.MasterPageTemplateDao;
import vn.pyco.tinycms.model.MasterPageTemplate;
import vn.pyco.tinycms.services.MasterPageTemplateService;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Service(MasterPageTemplateService.SERVICE_ID)
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MasterPageTemplateServiceImpl implements MasterPageTemplateService {

    @Autowired
    private MasterPageTemplateDao _masterPageTemplateDao;

    @Transactional
    @Override
    public void save(MasterPageTemplate template) {
        _masterPageTemplateDao.save(template);
    }
}
