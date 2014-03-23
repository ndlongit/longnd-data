<%@ taglib prefix="s" uri="/struts-tags"%>

<h2>Left menu</h2>
<ul>
	<li><a href="<s:url value="/home" />"><s:text
				name="common.menu.home" /></a></li>
	<li><a href="<s:url value="/user/create" />"><s:property
				value='getText("common.action.createItem", {"User"})' /></a></li>
	<li><a href="<s:url value="/user/list" />"><s:text
				name="menu.list.user" /></a></li>
	<li><a href="<s:url value="/employee/create" />"><s:property
				value='getText("common.action.createItem", {"Employee"})' /></a></li>
	<li><a href="<s:url value="/employee/list" />">List Employees</a></li>
	<li><a href="<s:url value="/department/create" />">Create
			Department</a></li>
	<li><a href="<s:url value="/department/list" />">List
			Departments</a></li>
</ul>
