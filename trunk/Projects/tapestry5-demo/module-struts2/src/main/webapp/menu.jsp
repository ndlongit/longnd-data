<%@ taglib prefix="s" uri="/struts-tags"%>

<h2>Left menu</h2>
<ul>
	<li><a href="<s:url value="/home" />"><s:text
				name="common.menu.home" /></a></li>
	<li><a href="<s:url value="/admin/create" />"><s:text
				name="common.action.create" /> User</a></li>
	<li><a href="<s:url value="/admin/list" />"><s:text
				name="menu.list.user" /></a></li>
	<li><a href="<s:url value="/employee/create" />">Create
			Employee</a></li>
	<li><a href="<s:url value="/employee/list" />">List Employees</a></li>
</ul>
