// [LICENCE-HEADER]
//

package vn.pyco.commons.model;

import java.io.Serializable;

/**
 * Interface marks class which have default flag. DAO will check if there is only
 * one default object using <code>getExample()</code> method.
 *
 */

public interface Defaultable<T extends Serializable> {
    /**
     * Property which represents default flag.
     */
	public static final String PROP_DEFAULT = "default";
        
    /**
     * Check if object is default one.
     *
     * @return true when object is default one
     */
    boolean isDefault();

    /**
     * Set object as default one.
     *
     * @param default value of default flag
     * @see #getExample()
     */
    void setDefault(boolean defaulz);

    /**
     * Get examplary object using in <code>GenericDao.setAsDefault()</code> method.
     *
     * Examplary object should have setted only this properties which are commons in group
     * with one default object, for example parent or category.
     *
     * If method returns null none of entities will be changed into not default.
     *
     * @see GenericDao#setAsDefault(Defaultable)
     * @return examplary object
     */
    Identifiable<T> getExample();
}
