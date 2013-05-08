// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.commons.model.impl.NumericIdEntity;
import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Entity
@Table(name=EntityConstants.TBL_CONTENT_PROPERTIES)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region=CacheConstants.REGION_CONTENTS)
public class ContentProperty extends NumericIdEntity {
    private static final long serialVersionUID = 1L;

    private String _code;
    private String _name;
    private String _constraints;
    private int _order;
    private ContentType _contentType;
    private PropertyType _propertyType;
    
    /**
     * @return the code
     */
    @Column(name="CODE", length=32, nullable=false)
    public String getCode() {
        return _code;
    }
    
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        _code = code;
    }

    /**
     * @return the name
     */
    @Column(name="NAME", length=128)
    public String getName() {
        return _name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        _name = name;
    }
    
    /**
     * @return the constraints
     */
    @Column(name="CONSTRAINTS", length=255)
    public String getConstraints() {
        return _constraints;
    }
    
    /**
     * @param constraints the constraints to set
     */
    public void setConstraints(String constraints) {
        _constraints = constraints;
    }
    
    /**
     * @return the order
     */
    @Column(name="`ORDER`")
    public int getOrder() {
        return _order;
    }
    
    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        _order = order;
    }

    /**
     * @return the contentType
     */
    @ManyToOne(targetEntity=ContentType.class, fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="CONTENT_TYPE_ID")
    public ContentType getContentType() {
        return _contentType;
    }

    /**
     * @param contentType
     *            the contentType to set
     */
    public void setContentType(ContentType contentType) {
        _contentType = contentType;
    }

    /**
     * @return the propertyType
     */
    @ManyToOne(targetEntity=PropertyType.class, fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="PROPERTY_TYPE_ID")
    public PropertyType getPropertyType() {
        return _propertyType;
    }

    /**
     * @param propertyType
     *            the propertyType to set
     */
    public void setPropertyType(PropertyType propertyType) {
        _propertyType = propertyType;
    }
}
