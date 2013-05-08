// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import vn.pyco.tinycms.model.ContentPageTemplate;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface ContentPageTemplateService {
    String SERVICE_ID = "contentPageTemplateService";

    void save(ContentPageTemplate template);
}
