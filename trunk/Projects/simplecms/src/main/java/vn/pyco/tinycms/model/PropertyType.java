// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name=EntityConstants.TBL_PROPERTY_TYPES)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region=CacheConstants.REGION_CONTENTS)
public class PropertyType extends NumericIdEntity {
    private static final long serialVersionUID = 1L;
    public static final String PROP_NAME = "name";
    private String _name;
    private String _renderPath;

    /**
     * @return the name
     */
    @Column(name="NAME", length=128, nullable=false)
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
     * @return the renderPath
     */
    @Column(name="RENDER_PATH", length=255)
    public String getRenderPath() {
        return _renderPath;
    }

    /**
     * @param renderPath
     *            the renderPath to set
     */
    public void setRenderPath(String renderPath) {
        _renderPath = renderPath;
    }

}
