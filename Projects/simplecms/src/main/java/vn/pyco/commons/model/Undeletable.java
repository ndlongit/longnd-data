// [LICENCE-HEADER]
//

package vn.pyco.commons.model;

/**
 * Interface marks class which cannot be deleted. If someone calls one of DAO's delete
 * methods object will be hidden instead of deleted.
 *
 */

public interface Undeletable {
    /**
     * Property which represents deleted flag.
     */
	public static final String PROP_DELETED = "deleted";
        
    /**
     * Check if object is deleted.
     *
     * @return true when object is hidden
     */
    boolean isDeleted();

    /**
     * Set object as default one.
     *
     * @param deleted value of hidden flag
     */
    void setDeleted(boolean deleted);
}
