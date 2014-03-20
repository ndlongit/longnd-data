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

		<div id="error" class="error">
			<s:actionerror />
		</div>

		<s:form action="%{action}" validate="true">
			<s:fielderror fieldName="loginName" />
			<s:textfield id="loginName" key="user.loginName"
				name="user.loginName" maxlength="20" />

			<s:fielderror fieldName="password" />
			<s:password id="password" key="user.password" name="user.password" />

			<s:fielderror fieldName="password2" />
			<s:password id="password2" key="user.password2" name="password2" />

			<s:fielderror fieldName="firstName" />
			<s:textfield id="firstName" key="user.firstName"
				name="user.firstName" />

			<s:fielderror fieldName="lastName" />
			<s:textfield id="lastName" key="user.lastName" name="user.lastName" />

			<s:fielderror fieldName="email" />
			<s:textfield id="email" key="user.email" name="user.email" />
			<%-- 			<s:reset value="Reset" /> --%>
			<s:if
				test="%{action==@org.java.demo.action.admin.UserAction@ACTION_DO_CREATE}">
				<s:submit name="create" key="common.action.create" align="center" />
			</s:if>
			<s:elseif
				test="%{action==@org.java.demo.action.admin.UserAction@ACTION_DO_COPY}">
				<s:submit name="copy"
					value='%{getText("common.action.copyItem", {"User"})}'
					align="center" />
			</s:elseif>
			<s:elseif
				test="%{action==@org.java.demo.action.admin.UserAction@ACTION_DO_EDIT}">
				<s:submit name="edit" key="common.action.edit" align="center" />
			</s:elseif>
			<!-- Hidden fields - Begin -->
			<s:textfield id="actionName" name="action" readonly="true"
				cssStyle="display: none;" />
			<s:textfield id="id" name="user.id" readonly="true"
				cssStyle="display: none;" />
			<!-- Hidden fields - End -->
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>
