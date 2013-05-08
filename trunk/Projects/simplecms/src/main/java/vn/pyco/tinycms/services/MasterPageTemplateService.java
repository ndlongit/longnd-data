// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import vn.pyco.tinycms.model.MasterPageTemplate;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface MasterPageTemplateService {
    String SERVICE_ID = "masterPageTemplateService";

    void save(MasterPageTemplate template);
}
