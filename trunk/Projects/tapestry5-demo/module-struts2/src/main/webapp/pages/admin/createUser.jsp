<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="tiles.default">

	<tiles:putAttribute name="title">
		<s:property value="pageTitle" />
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<script>
			function ready() {

				//Do somethings after page loaded
				//alert('Page loaded');
			}
		</script>

		<h2>
			<s:property value="headerText" />
		</h2>
		<s:actionerror />

		<s:form action="%{action}" validate="false">
			<s:textfield id="loginName" key="account.loginName"
				name="user.loginName" />
			<s:password id="password" key="account.password" name="user.password" />
			<s:password id="password2" key="account.password2" name="password2" />
			<s:textfield id="firstName" key="user.firstName"
				name="user.firstName" />
			<s:textfield id="lastName" key="user.lastName" name="user.lastName" />
			<s:textfield id="email" key="user.email" name="user.email" />
			<%-- 			<s:reset value="Reset" /> --%>
			<s:submit value="%{submitButtonLabel}" />

			<!-- Hidden section -->
			<div id="hiddenDiv" style="display: none;">
				<s:textfield id="action" name="action" readonly="true" />
				<s:textfield id="id" name="user.id" readonly="true" />
			</div>
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>
