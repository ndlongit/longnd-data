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
			<s:textfield id="loginName" key="employee.loginName"
				name="employee.loginName" maxlength="20" />

			<s:fielderror fieldName="password" />
			<s:password id="password" key="employee.password"
				name="employee.password" />

			<s:fielderror fieldName="password2" />
			<s:password id="password2" key="employee.password2" name="password2" />

			<s:fielderror fieldName="firstName" />
			<s:textfield id="firstName" key="employee.firstName"
				name="employee.firstName" />

			<s:fielderror fieldName="lastName" />
			<s:textfield id="lastName" key="employee.lastName"
				name="employee.lastName" />

			<s:fielderror fieldName="email" />
			<s:textfield id="email" key="employee.email" name="employee.email" />
			<%-- 			<s:reset value="Reset" /> --%>
			<s:if
				test="%{action==@org.java.demo.action.base.AbstractAction@ACTION_DO_CREATE}">
				<s:submit name="create" key="common.action.create" align="center" />
			</s:if>
			<s:elseif
				test="%{action==@org.java.demo.action.base.AbstractAction@ACTION_DO_COPY}">
				<s:submit name="copy"
					value='%{getText("common.action.copyItem", {"User"})}'
					align="center" />
			</s:elseif>
			<s:elseif
				test="%{action==@org.java.demo.action.base.AbstractAction@ACTION_DO_EDIT}">
				<s:submit name="edit" key="common.action.edit" align="center" />
			</s:elseif>

			<!-- Hidden fields - Begin -->
			<table class="hidden">
				<tr>
					<td><s:textfield id="actionName" name="action" /> <s:textfield
							id="id" name="employee.id" /></td>
				</tr>
			</table>
			<!-- Hidden fields - End -->
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>
