// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.tinycms.utils.CacheConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
@DiscriminatorValue("MASTER")
public class MasterPageTemplate extends PageTemplate {
    private static final long serialVersionUID = 1L;

    private String _cssCode = "";
    private String _jsCode = "";
    
    /**
     * @return the cssCode
     */
    @Column(name="CSS_CODE", length=10240, columnDefinition="TEXT")
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
    @Column(name="JS_CODE", length=10240, columnDefinition="TEXT")
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
