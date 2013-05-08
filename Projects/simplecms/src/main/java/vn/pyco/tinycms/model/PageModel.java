// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

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
@Table(name=EntityConstants.TBL_PAGE_MODELS, uniqueConstraints={@UniqueConstraint(columnNames={"PAGE_ID", "NAME"})})
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
public class PageModel extends StatefulEntity {
    private static final long serialVersionUID = 1L;
    
    private Page _page;
    private String _name;
    private String _value;
    private String _type;
    
    /**
     * @return the page
     */
    @ManyToOne(targetEntity=Page.class, fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="PAGE_ID")
    public Page getPage() {
        return _page;
    }
    
    /**
     * @param page the page to set
     */
    public void setPage(Page page) {
        _page = page;
    }
    
    /**
     * @return the name
     */
    @Column(name="NAME", length=128, nullable=false)
    public String getName() {
        return _name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        _name = name;
    }
    
    /**
     * @return the value
     */
    @Column(name="VALUE", length=1024, nullable=false)
    public String getValue() {
        return _value;
    }
    
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        _value = value;
    }
    
    /**
     * @return the type
     */
    @Column(name="TYPE", length=255)
    public String getType() {
        return _type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        _type = type;
    }
    
    @Transient
    public int getUniqueCode() {
        String code = String.format("%d-%s-%s-%s", _page.getId(), _name, _value, _type);
        
        return code.hashCode();
    }
}
