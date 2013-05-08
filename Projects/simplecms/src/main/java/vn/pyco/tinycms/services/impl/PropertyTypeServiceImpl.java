// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.PropertyTypeDao;
import vn.pyco.tinycms.model.PropertyType;
import vn.pyco.tinycms.services.PropertyTypeService;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Service(PropertyTypeService.SERVICE_ID)
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PropertyTypeServiceImpl implements PropertyTypeService {
    @Autowired
    private PropertyTypeDao _propertyTypeDao;

    @Transactional
    public List<PropertyType> findAll() {
        return _propertyTypeDao.getAll();
    }

    public PropertyType getPropertyTypeByName(String name) {
        return _propertyTypeDao.getPropertyTypeByName(name);
    }
}
