// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import java.util.Map;

import vn.pyco.tinycms.model.Content;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface ContentService {
    String SERVICE_ID = "contentService";

    Map<String, Object> buildContentDataMap(int contentId);

    void saveContent(Content content);

    Content getObjectByCriteria(String name, String value);

    Content getObjectByCriteria(String[] names, String[] values);
}
