// [LICENCE-HEADER]
//
package vn.pyco.tinycms.services.init;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.pyco.tinycms.dao.ContentDao;
import vn.pyco.tinycms.dao.ContentTypeDao;
import vn.pyco.tinycms.dao.PropertyTypeDao;
import vn.pyco.tinycms.model.Content;
import vn.pyco.tinycms.model.ContentType;
import vn.pyco.tinycms.model.PropertyType;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class ContentDataInitializer extends AbstractDataInitializer {
    @Autowired
    private PropertyTypeDao _propertyTypeDao;
    
    @Autowired
    private ContentTypeDao _contentTypeDao;
    
    @Autowired
    private ContentDao _contentDao;
    
    private Set<PropertyType> _propertyTypes;
    private Set<ContentType> _contentTypes;
    private Set<Content> _contents;
    
    /**
     * @param propertyTypes the propertyTypes to set
     */
    @Required
    public void setPropertyTypes(Set<PropertyType> propertyTypes) {
        _propertyTypes = propertyTypes;
    }
    
    /**
     * @param contentTypes the contentTypes to set
     */
    @Required
    public void setContentTypes(Set<ContentType> contentTypes) {
        _contentTypes = contentTypes;
    }
    
    /**
     * @param contents the contents to set
     */
    @Required
    public void setContents(Set<Content> contents) {
        _contents = contents;
    }
    
    /*
     * @see vn.pyco.tinycms.services.DataInitializer#initialize()
     */
    @Override
    @Transactional(readOnly=false)
    public void initialize() throws Exception {
        for (PropertyType propertyType : _propertyTypes) {
            _propertyTypeDao.save(propertyType);
        }
        
        for (ContentType contentType : _contentTypes) {
            _contentTypeDao.save(contentType);
        }
        
        for (Content content : _contents) {
            _contentDao.save(content);
        }
    }
}
