// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import java.util.List;

import vn.pyco.tinycms.model.ContentType;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface ContentTypeService {
    String SERVICE_ID = "contentTypeService";

    void saveContentType(ContentType contentType);
    public List<ContentType> findAll();

    ContentType getObjectByCriteria(String name, String value);

    ContentType getObjectByCriteria(String[] names, String[] values);
}
