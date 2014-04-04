<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="tiles.default">

	<tiles:putAttribute name="title">List Employees</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<script>
			function ready() {

				//Do somethings after page loaded
				//alert('Page loaded');
			}

			function view(url) {
				openPopup(url, 800, 600, 'viewEmployee');
			}

			function viewDepartment(url) {
				openPopup(url, 800, 600, 'viewDepartment');
			}
		</script>

		<h2>Employee list</h2>
		<table class="alternateRow hover border-all">
			<thead>
				<tr>
					<th width="5%"><s:text name="common.index.NO" /></th>
					<th width="20%"><s:text name="loginName" /></th>
					<th width="15%"><s:text name="firstName" /></th>
					<th width="15%"><s:text name="lastName" /></th>
					<th width="20%"><s:text name="email" /></th>
					<th width="10%"><s:text name="department" /></th>
					<th width="15%"><s:text name="common.action" /></th>
				</tr>
			</thead>
			<s:if test="employees.size > 0">
				<s:iterator value="employees" status="status">
					<tr id="row_<s:property value="id"/>">
						<td align="center">${status.index + 1}</td>
						<td><s:url id="view"
								action="%{@org.java.demo.web.action.base.AbstractAction@ACTION_VIEW}">
								<s:param name="id" value="id" />
							</s:url> <s:a href="javascript:view('%{view}')"
								title='%{getText("common.action.viewItem",{"Employee"})}'>
								<s:property value="loginName" />
							</s:a>&nbsp;</td>
						<td><s:property value="firstName" />&nbsp;</td>
						<td><s:property value="lastName" />&nbsp;</td>
						<td><s:if test="email != null && email != ''">
								<a
									href="mailto:<s:property value="email"/>?subject=Email%20Subject"
									title="Click to send an email"><s:property value="email" /></a>
							</s:if>&nbsp;</td>
						<td><s:url id="viewDepartment"
								namespace="%{@org.java.demo.web.action.department.DepartmentAction@NAME_SPACE}"
								action="%{@org.java.demo.web.action.base.AbstractAction@ACTION_VIEW}">
								<s:param name="id" value="department.id" />
							</s:url> <s:a href="javascript:viewDepartment('%{viewDepartment}')"
								title='%{getText("common.action.viewItem",{"Department"})}'>
								<s:property value="department.name" />
							</s:a>&nbsp;</td>
						<td align="center"><%@ include
								file="/WEB-INF/jspf/action-buttons.jspf"%></td>
					</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<tr>
					<td colspan="6" align="center"><s:text
							name="common.text.noData" /></td>
				</tr>
			</s:else>
		</table>

		<s:url id="create"
			action="%{@org.java.demo.web.action.base.AbstractAction@ACTION_CREATE}" />

		<s:a href="%{create}">
			<s:property value='getText("common.action.createItem", {"Employee"})' />
		</s:a>
	</tiles:putAttribute>

</tiles:insertDefinition>