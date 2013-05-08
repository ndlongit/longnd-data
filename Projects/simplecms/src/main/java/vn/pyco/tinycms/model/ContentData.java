// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name=EntityConstants.TBL_CONTENT_DATA, uniqueConstraints={@UniqueConstraint(columnNames={"CONTENT_ID", "CODE"})})
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
public class ContentData extends NumericIdEntity {
    private static final long serialVersionUID = 1L;

    private Content _content;
    private String _code;
    private String _data;

    /**
     * @return the content
     */
    @ManyToOne(targetEntity=Content.class, fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="CONTENT_ID")
    public Content getContent() {
        return _content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(Content content) {
        _content = content;
    }

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
     * @return the data
     */
    @Column(name="DATA", length=10240, nullable=false, columnDefinition="TEXT")
    public String getData() {
        return _data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data) {
        _data = data;
    }

}
