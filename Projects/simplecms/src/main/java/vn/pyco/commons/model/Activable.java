// [LICENCE-HEADER]
//

package vn.pyco.commons.model;

/**
 * Interface marks class which can be enabled or disabled.
 * 
 */

public interface Activable {
    /**
     * Property which represents active flag.
     */
    public static final String PROP_ACTIVE = "active";
    
    /**
     * Check if object is active.
     * 
     * @return true when object is active
     */
    boolean isActive();

    /**
     * Set object's active flag.
     * 
     * @param active
     *            value of active flag
     */
    void setActive(boolean active);
}
