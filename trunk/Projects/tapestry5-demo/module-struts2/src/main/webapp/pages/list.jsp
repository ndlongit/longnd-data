<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="title.default">
	<tiles:putAttribute name="body">

		<h2>Employee list</h2>
		<s:if test="employees.size > 0">
			<table border="1" width="60%">
				<tr>
					<th width="30%"><s:text name="firstName" /></th>
					<th width="30%"><s:text name="lastName" /></th>
					<th width="20%">Actions</th>
				</tr>
				<s:iterator value="employees">
					<tr id="row_<s:property value="id"/>">
						<td><s:property value="firstName" />&nbsp;</td>
						<td><s:property value="lastName" />&nbsp;</td>
						<td align="center"><s:url id="removeUrl"
								action="delete-employee">
								<s:param name="id" value="id" />
							</s:url> <s:a href="%{removeUrl}" targets="persons">Delete</s:a> <s:url
								id="editUrl" action="edit-employee"> | 
						<s:param name="id" value="id" />
							</s:url> <s:a href="%{editUrl}" targets="persons">Edit</s:a> <%-- 					<s:a id="a_%{id}" notifyTopics="/edit">Edit</s:a> --%>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
	</tiles:putAttribute>

</tiles:insertDefinition>