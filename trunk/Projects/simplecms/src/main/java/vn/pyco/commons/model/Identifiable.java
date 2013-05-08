// [LICENCE-HEADER]
//

package vn.pyco.commons.model;

import java.io.Serializable;

/**
 * Interface marks class which can be persisted.
 * 
 * @param <I> type of primary key, it must be serializable
 * 
 */

public interface Identifiable<I extends Serializable> extends Serializable {
    /**
     * Property which represents identify.
     */
	public static final String PROP_ID = "id";
        
    /**
     * Get primary key.
     * 
     * @return primary key
     */
    I getId();

    /**
     * Set primary key.
     * 
     * @param id
     *            primary key
     */
    void setId(I id);
}
