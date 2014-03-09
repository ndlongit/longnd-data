<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="title.default">
	<tiles:putAttribute name="body">
		<h2>Create User</h2>
		<s:url action="list" id="descrsUrl" />

		<s:form action="do-create-user" validate="false">
			<s:textfield id="id" name="user.id" cssStyle="display:none" />

			<s:textfield id="loginName" key="account.loginName"
				name="user.loginName" />
			<s:password id="password" key="account.password" name="user.password" />
			<s:password id="password2" key="account.password2" name="password2" />
			<s:textfield id="firstName" key="user.firstName"
				name="user.firstName" />
			<s:textfield id="lastName" key="user.lastName" name="user.lastName" />
			<s:textfield id="email" key="user.email" name="user.email" />
			<s:submit />
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>