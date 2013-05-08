// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Entity
@Table(name=EntityConstants.TBL_SITES)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
public class Site extends Node {
    private static final long serialVersionUID = 1L;

    private String _description;
    private String _cssCode = "";
    private String _jsCode = "";
    
    /**
     * @return the description
     */
    @Column(name="DESCRIPTION", length=255, nullable=false)
    public String getDescription() {
        return _description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        _description = description;
    }
    
    /**
     * @return the cssCode
     */
    @Column(name="CSS_CODE", length=10240, nullable=false)
    public String getCssCode() {
        return _cssCode;
    }
    
    /**
     * @param cssCode the cssCode to set
     */
    public void setCssCode(String cssCode) {
        _cssCode = cssCode;
    }
    
    /**
     * @return the jsCode
     */
    @Column(name="JS_CODE", length=10240, nullable=false)
    public String getJsCode() {
        return _jsCode;
    }
    
    /**
     * @param jsCode the jsCode to set
     */
    public void setJsCode(String jsCode) {
        _jsCode = jsCode;
    }
}
