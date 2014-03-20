<%@ taglib prefix="s" uri="/struts-tags"%>

<h2>Left menu</h2>
<ul>
	<li><a href="<s:url value="/home" />"><s:text
				name="common.menu.home" /></a></li>
	<li><a href="<s:url value="/admin/create-user" />"><s:text
				name="menu.user.create.text" /></a></li>
	<li><a href="<s:url value="/admin/list-users" />"><s:text
				name="menu.user.list.text" /></a></li>
</ul>
