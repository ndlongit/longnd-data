// [LICENCE-HEADER]
//
package vn.pyco.tinycms.dao;

import vn.pyco.commons.dao.GenericDao;
import vn.pyco.tinycms.model.PropertyType;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public interface PropertyTypeDao extends GenericDao<PropertyType, Integer> {
    String SERVICE_ID = "propertyTypeDao";

    public PropertyType getPropertyTypeByName(String name);
}