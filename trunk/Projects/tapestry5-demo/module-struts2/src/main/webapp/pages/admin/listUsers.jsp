<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="tiles.default">

	<tiles:putAttribute name="title">List Users</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<script>
			function ready() {

				//Do somethings after page loaded
				//alert('Page loaded');
			}

			function viewUser(url, userId) {
				openPopup(url, 800, 600, 'viewUser');
			}
		</script>

		<h2>Users list</h2>
		<table border="1" width="100%">
			<tr>
				<th width="6%"><s:text name="common.index.NO" /></th>
				<th width="20%"><s:text name="user.loginName" /></th>
				<th width="20%"><s:text name="user.firstName" /></th>
				<th width="20%"><s:text name="user.lastName" /></th>
				<th width="20%"><s:text name="user.email" /></th>
				<th width="12%">Actions</th>
			</tr>
			<s:if test="users.size > 0">
				<s:iterator value="users" status="status">
					<tr id="row_<s:property value="id"/>">
						<td align="center">${status.index + 1}</td>
						<td><s:url id="view" action="view-user">
								<s:param name="id" value="id" />
							</s:url> <s:a href="javascript:viewUser('%{view}')"
								title="View User Detail">
								<s:property value="loginName" />
							</s:a>&nbsp;</td>
						<td><s:property value="firstName" />&nbsp;</td>
						<td><s:property value="lastName" />&nbsp;</td>
						<td><s:if test="email != null && email != ''">
								<a
									href="mailto:<s:property value="email" />?subject=Email%20Subject"><s:property
										value="email" /></a>
							</s:if>&nbsp;</td>
						<td align="center"><s:url id="editUser" action="edit-user">
								<s:param name="id" value="id" />
							</s:url> <s:a href="%{editUser}" title="Edit User">Edit</s:a> | <s:url
								id="deleteUser" action="delete-user">
								<s:param name="id" value="id" />
							</s:url> <s:a href="%{deleteUser}" title="Delete User">Delete</s:a></td>
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

		<s:url id="createUser" action="create-user" />

		<s:a href="%{createUser}">
			<s:text name="menu.user.create.text" />
		</s:a>
		<%-- 		<a href="<s:url value="/admin/create-user" />"><s:text --%>
		<%-- 				name="menu.user.create.text" /></a> --%>
		<%-- 		<s:property value='@org.java.demo.action.admin.UserAction@ACTION_CREATE' /> --%>
	</tiles:putAttribute>

</tiles:insertDefinition>