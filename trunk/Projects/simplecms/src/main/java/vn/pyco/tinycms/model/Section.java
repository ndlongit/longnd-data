// [LICENCE-HEADER]
//
package vn.pyco.tinycms.model;

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
@Table(name=EntityConstants.TBL_SECTIONS)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONTENTS)
public class Section extends Node {
    private static final long serialVersionUID = 1L;
    
}
