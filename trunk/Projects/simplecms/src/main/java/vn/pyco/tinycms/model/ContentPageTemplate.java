// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

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
@DiscriminatorValue("CONTENT")
public class ContentPageTemplate extends PageTemplate {
    private static final long serialVersionUID = 1L;
    
}
