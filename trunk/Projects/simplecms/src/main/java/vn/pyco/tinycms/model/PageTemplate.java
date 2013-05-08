// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = EntityConstants.TBL_PAGE_TEMPLATES)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_CONTENTS)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 7)
public class PageTemplate extends StatefulEntity {
    private static final long serialVersionUID = 1L;

    public enum Type {
        Master, Content
    }

    private String _template;

    /**
     * @return the template
     */
    @Column(name = "TEMPLATE", length = 10240, columnDefinition = "TEXT", nullable = false)
    public String getTemplate() {
        return _template;
    }

    /**
     * @param template
     *            the template to set
     */
    public void setTemplate(String template) {
        _template = template;
    }
}
