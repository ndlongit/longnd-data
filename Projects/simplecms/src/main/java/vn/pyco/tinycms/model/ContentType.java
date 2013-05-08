// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.commons.model.impl.StatefulEntity;
import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
@Entity
@Table(name = EntityConstants.TBL_CONTENT_TYPES)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_CONTENTS)
public class ContentType extends StatefulEntity {
    private static final long serialVersionUID = 1L;

    private String _name;
    private String _description;
    private List<ContentProperty> _properties;

    /**
     * @return the name
     */
    @Column(name = "NAME", unique = true, length = 128, nullable = false)
    public String getName() {
        return _name;
    }

    /**
     * @param name
     *            the name to set
     */
    @Validate("required")
    public void setName(String name) {
        _name = name;
    }

    /**
     * @return the description
     */
    @Column(name = "DESCRIPTION", length = 1024)
    public String getDescription() {
        return _description;
    }

    /**
     * @param description
     *            the description to set
     */
    @Validate("required")
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * @return the properties
     */
    @OneToMany(targetEntity = ContentProperty.class, mappedBy = "contentType", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    public List<ContentProperty> getProperties() {
        return _properties;
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(List<ContentProperty> properties) {
        for (ContentProperty property : properties) {
            property.setContentType(this);
        }
        _properties = properties;
    }

    public void addProperty(ContentProperty property) {
        this.addProperty(0, property);
    }

    public void addProperty(int pos, ContentProperty property) {
        if (_properties == null) {
            _properties = new ArrayList<ContentProperty>();
        }

        if (!_properties.contains(property)) {
            property.setContentType(this);
            this._properties.add(pos, property);
        }
    }
}
