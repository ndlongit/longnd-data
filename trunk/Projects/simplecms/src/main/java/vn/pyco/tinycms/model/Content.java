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
@Table(name=EntityConstants.TBL_CONTENTS)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region=CacheConstants.REGION_CONTENTS)
public class Content extends StatefulEntity {
    private static final long serialVersionUID = 1L;

    private String _name;
    private String _description;
    private ContentType _contentType;
    private List<ContentData> _data;

    /**
     * @return the name
     */
    @Column(name="NAME", unique=true, length=128, nullable=false)
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
     * @return the description
     */
    @Column(name="DESCRIPTION", length=1024)
    public String getDescription() {
        return _description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * @return the contentType
     */
    @ManyToOne(targetEntity=ContentType.class, fetch=FetchType.EAGER, optional=false)
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
     * @return the data
     */
    @OneToMany(targetEntity=ContentData.class, mappedBy="content", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    public List<ContentData> getData() {
        return _data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(List<ContentData> data) {
        for (ContentData aData : data) {
            aData.setContent(this);
        }
        _data = data;
    }

}
