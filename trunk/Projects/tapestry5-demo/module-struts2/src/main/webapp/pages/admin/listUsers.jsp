<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="title.default">
	
	<tiles:putAttribute name="title">List Users</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<script>
			function ready() {

				//Do somethings after page loaded
				//alert('Page loaded');
			}
		</script>

		<h2>Users list</h2>
		<table border="1" width="100%">
			<tr>
				<th width="10%"><s:text name="common.index.NO" /></th>
				<th width="15%"><s:text name="account.loginName" /></th>
				<th width="15%"><s:text name="user.firstName" /></th>
				<th width="15%"><s:text name="user.lastName" /></th>
				<th width="25%"><s:text name="user.email" /></th>
				<th width="20%">Actions</th>
			</tr>
			<s:if test="users.size > 0">
				<s:iterator value="users" status="status">
					<tr id="row_<s:property value="id"/>">
						<td align="center">${status.index + 1}</td>
						<td><s:property value="loginName" />&nbsp;</td>
						<td><s:property value="firstName" />&nbsp;</td>
						<td><s:property value="lastName" />&nbsp;</td>
						<td><a href="mailto:<s:property value="email" />"><s:property
									value="email" /></a>&nbsp;</td>
						<td align="center"><s:url id="editUrl" action="edit-user">
								<s:param name="id" value="id" />
							</s:url> <s:a href="%{editUrl}">Edit</s:a> | <%-- <s:a id="a_%{id}" notifyTopics="/edit">Edit</s:a> --%>
							<s:url id="removeUrl" action="delete-user">
								<s:param name="id" value="id" />
							</s:url> <s:a href="%{removeUrl}" title="Delete ...">Delete</s:a></td>
					</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<tr>
					<td colspan="6" align="center"><s:text
							name="common.data.notfound" /></td>
				</tr>
			</s:else>
		</table>

		<a href="<s:url value="create-user" />">Create New User</a>

	</tiles:putAttribute>

</tiles:insertDefinition>