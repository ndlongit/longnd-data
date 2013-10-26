/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.socialnetworking.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MeetupsRegistration service. Represents a row in the &quot;SNW_MeetupsRegistration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.socialnetworking.model.impl.MeetupsRegistrationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this interface directly. All methods that expect a meetups registration model instance should use the {@link MeetupsRegistration} interface instead.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistration
 * @see com.liferay.socialnetworking.model.impl.MeetupsRegistrationImpl
 * @see com.liferay.socialnetworking.model.impl.MeetupsRegistrationModelImpl
 * @generated
 */
public interface MeetupsRegistrationModel extends BaseModel<MeetupsRegistration> {
	/**
	 * Gets the primary key of this meetups registration.
	 *
	 * @return the primary key of this meetups registration
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this meetups registration
	 *
	 * @param pk the primary key of this meetups registration
	 */
	public void setPrimaryKey(long pk);

	/**
	 * Gets the meetups registration id of this meetups registration.
	 *
	 * @return the meetups registration id of this meetups registration
	 */
	public long getMeetupsRegistrationId();

	/**
	 * Sets the meetups registration id of this meetups registration.
	 *
	 * @param meetupsRegistrationId the meetups registration id of this meetups registration
	 */
	public void setMeetupsRegistrationId(long meetupsRegistrationId);

	/**
	 * Gets the company id of this meetups registration.
	 *
	 * @return the company id of this meetups registration
	 */
	public long getCompanyId();

	/**
	 * Sets the company id of this meetups registration.
	 *
	 * @param companyId the company id of this meetups registration
	 */
	public void setCompanyId(long companyId);

	/**
	 * Gets the user id of this meetups registration.
	 *
	 * @return the user id of this meetups registration
	 */
	public long getUserId();

	/**
	 * Sets the user id of this meetups registration.
	 *
	 * @param userId the user id of this meetups registration
	 */
	public void setUserId(long userId);

	/**
	 * Gets the user uuid of this meetups registration.
	 *
	 * @return the user uuid of this meetups registration
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this meetups registration.
	 *
	 * @param userUuid the user uuid of this meetups registration
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Gets the user name of this meetups registration.
	 *
	 * @return the user name of this meetups registration
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this meetups registration.
	 *
	 * @param userName the user name of this meetups registration
	 */
	public void setUserName(String userName);

	/**
	 * Gets the create date of this meetups registration.
	 *
	 * @return the create date of this meetups registration
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this meetups registration.
	 *
	 * @param createDate the create date of this meetups registration
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Gets the modified date of this meetups registration.
	 *
	 * @return the modified date of this meetups registration
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this meetups registration.
	 *
	 * @param modifiedDate the modified date of this meetups registration
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Gets the meetups entry id of this meetups registration.
	 *
	 * @return the meetups entry id of this meetups registration
	 */
	public long getMeetupsEntryId();

	/**
	 * Sets the meetups entry id of this meetups registration.
	 *
	 * @param meetupsEntryId the meetups entry id of this meetups registration
	 */
	public void setMeetupsEntryId(long meetupsEntryId);

	/**
	 * Gets the status of this meetups registration.
	 *
	 * @return the status of this meetups registration
	 */
	public int getStatus();

	/**
	 * Sets the status of this meetups registration.
	 *
	 * @param status the status of this meetups registration
	 */
	public void setStatus(int status);

	/**
	 * Gets the comments of this meetups registration.
	 *
	 * @return the comments of this meetups registration
	 */
	@AutoEscape
	public String getComments();

	/**
	 * Sets the comments of this meetups registration.
	 *
	 * @param comments the comments of this meetups registration
	 */
	public void setComments(String comments);

	/**
	 * Gets a copy of this meetups registration as an escaped model instance by wrapping it with an {@link com.liferay.portal.kernel.bean.AutoEscapeBeanHandler}.
	 *
	 * @return the escaped model instance
	 * @see com.liferay.portal.kernel.bean.AutoEscapeBeanHandler
	 */
	public MeetupsRegistration toEscapedModel();

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(MeetupsRegistration meetupsRegistration);

	public int hashCode();

	public String toString();

	public String toXmlString();
}