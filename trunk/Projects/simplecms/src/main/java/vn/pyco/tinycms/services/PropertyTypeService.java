// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services;

import java.util.List;

import vn.pyco.tinycms.model.PropertyType;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface PropertyTypeService {
    String SERVICE_ID = "propertyTypeService";

    public List<PropertyType> findAll();

    public PropertyType getPropertyTypeByName(String name);
}
