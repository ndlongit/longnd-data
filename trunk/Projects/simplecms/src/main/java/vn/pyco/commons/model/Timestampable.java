package vn.pyco.commons.model;

import java.util.Date;

public interface Timestampable {
	public static final String PROP_CREATED_DATE="createdDate";
	public static final String PROP_UPDATED_DATE="updatedDate";
	public static final String PROP_UPDATED_BY="updatedBy";
	public static final String PROP_CREATED_BY="createdBy";
	/**
	 * @return the creation date 
	 */
	Date getCreatedDate();
	
	/**
	 * @return the last update date
	 */
	Date getUpdatedDate();
	
	/**
	 * @param createdDate the creation date to set
	 */
	void setCreatedDate(Date createdDate);
	
	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	void setUpdatedDate(Date updatedDate);
	
	/**
	 * @return the last User that has updated the entry 
	 */
	String getUpdatedBy();
	
	/**
	 * @param user the last User that has updated the entry 
	 */
	void setUpdatedBy(String updatedBy);
	
	/**
	 * @return the Username that has updated the entry 
	 */
    String getCreatedBy();
    
	/**
	 * @param user the last User that has updated the entry 
	 */
    void setCreatedBy(String createdBy);
}
