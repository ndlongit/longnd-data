// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import vn.pyco.commons.model.Defaultable;
import vn.pyco.commons.model.Identifiable;
import vn.pyco.commons.utils.HashHelper;
import vn.pyco.tinycms.utils.CacheConstants;
import vn.pyco.tinycms.utils.EntityConstants;

/**
 * <Briefly describing the purpose of the class/interface...>
 *
 */
@Entity
@Table(name=EntityConstants.TBL_PAGES)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
public class Page extends Node implements Defaultable<Integer> {
    private static final long serialVersionUID = 1L;
    
    private boolean _default;
    private MasterPageTemplate _masterTemplate;
    private ContentPageTemplate _contentTemplate;
    private List<PageModel> _models;
    
    /*
     * @see vn.pyco.commons.model.Defaultable#isDefault()
     */
    @Override
    @Column(name="IS_DEFAULT", nullable=false)
    public boolean isDefault() {
        return _default;
    }
    
    /*
     * @see vn.pyco.commons.model.Defaultable#setDefault(boolean)
     */
    @Override
    public void setDefault(boolean defaulz) {
        _default = defaulz;
    }
    
    /*
     * @see vn.pyco.commons.model.Defaultable#getExample()
     */
    @Override
    @Transient
    public Identifiable<Integer> getExample() {
        Node node = new Node();
        node.setParent(getParent());
        
        return node;
    }
    
    /**
     * @return the masterTemplate
     */
    @ManyToOne(targetEntity=MasterPageTemplate.class, fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="MASTER_TEMPLATE_ID")
    public MasterPageTemplate getMasterTemplate() {
        return _masterTemplate;
    }
    
    /**
     * @param masterTemplate the masterTemplate to set
     */
    public void setMasterTemplate(MasterPageTemplate masterTemplate) {
        _masterTemplate = masterTemplate;
    }
    
    /**
     * @return the contentTemplate
     */
    @ManyToOne(targetEntity=ContentPageTemplate.class, fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="CONTENT_TEMPLATE_ID")
    public ContentPageTemplate getContentTemplate() {
        return _contentTemplate;
    }
    
    /**
     * @param contentTemplate the contentTemplate to set
     */
    public void setContentTemplate(ContentPageTemplate contentTemplate) {
        _contentTemplate = contentTemplate;
    }
    
    /**
     * @return the models
     */
    @OneToMany(targetEntity=PageModel.class, mappedBy="page", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    public List<PageModel> getModels() {
        return _models;
    }
    
    /**
     * @param models the models to set
     */
    public void setModels(List<PageModel> models) {
        for (PageModel model : models) {
            model.setPage(this);
        }
        _models = models;
    }
    
    @Transient
    public String getETag() {
        // ETag : <id>-<updatedDate>[-<modeHashCode>]*
        StringBuilder eTag = new StringBuilder();
        
        eTag.append(getId());
        eTag.append("-" + getUpdatedDate().getTime());
        for (PageModel model : _models) {
            eTag.append("-" + model.getUniqueCode());
        }
                
        return HashHelper.md5Hash(eTag.toString().getBytes());
    }
    
}
