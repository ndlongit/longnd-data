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
		<s:form method="get">
			<s:textfield id="loginName" key="loginName" name="loginName"
				maxlength="20" />
			<s:textfield id="firstName" key="firstName" name="firstName" />
			<s:textfield id="lastName" key="lastName" name="lastName" />
			<s:textfield id="email" key="email" name="email" />
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>