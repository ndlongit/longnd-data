<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="tiles.default">

	<tiles:putAttribute name="title">
		<s:property value="pageTitle" />
	</tiles:putAttribute>

	<tiles:putAttribute name="body">

		<h2>
			<s:property value="headerText" />
		</h2>
		<s:form action="%{action}" validate="false">
			<s:textfield id="loginName" key="account.loginName"
				name="user.loginName" disabled="true" />
			<s:textfield id="firstName" key="user.firstName"
				name="user.firstName" disabled="true" />
			<s:textfield id="lastName" key="user.lastName" name="user.lastName"
				disabled="true" />
			<s:textfield id="email" key="user.email" name="user.email"
				disabled="true" />
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>