<%
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
%>

<%@ include file="/init.jsp" %>
		
<%
String redirect = ParamUtil.getString(request, "redirect");

long wallEntryId = ParamUtil.getLong(request, "wallEntryId");

WallEntry wallEntry = null;

try {
	wallEntry = WallEntryLocalServiceUtil.getWallEntry(wallEntryId);
}
catch (Exception ex) {
}
%>

<form action="<portlet:actionURL name="updateWallEntry"><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />updateWallEntry(); return false;">
<input name="<portlet:namespace />wallEntryId" type="hidden" value="<%= wallEntryId %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="Comments" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:input-field model="<%= WallEntry.class %>" bean="<%= wallEntry %>" field="comments" />
	</td>
</tr>

</table>

<br />

<input type="submit" value="<liferay-ui:message key="Update" />" />

<input type="button" value="<liferay-ui:message key="Cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />updateWallEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>