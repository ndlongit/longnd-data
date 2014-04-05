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
			<s:textfield id="loginName" key="loginName" name="loginName"
				maxlength="20" />

			<s:fielderror fieldName="password" />
			<s:password id="password" key="password" name="password"
				maxlength="15" />

			<s:fielderror fieldName="password2" />
			<s:password id="password2" key="password2" name="password2"
				maxlength="15" />

			<s:fielderror fieldName="firstName" />
			<s:textfield id="firstName" key="firstName" name="firstName" />

			<s:fielderror fieldName="lastName" />
			<s:textfield id="lastName" key="lastName" name="lastName" />

			<s:checkboxlist id="groups" list="groupModels" name="groupValues"
				listKey="id" listValue="name" key="groups" />

			<s:if
				test="%{action==@org.java.demo.web.action.base.AbstractAction@ACTION_DO_CREATE}">
				<s:submit name="create"
					value='%{getText("common.action.createItem", {"User"})}'
					align="center" />
			</s:if>
			<s:elseif
				test="%{action==@org.java.demo.web.action.base.AbstractAction@ACTION_DO_COPY}">
				<s:submit name="copy"
					value='%{getText("common.action.copyItem", {"User"})}'
					align="center" />
			</s:elseif>
			<s:elseif
				test="%{action==@org.java.demo.web.action.base.AbstractAction@ACTION_DO_EDIT}">
				<s:submit name="edit" key="common.action.edit" align="center" />
			</s:elseif>

			<!-- Hidden fields - Begin -->
			<table class="hidden">
				<tr>
					<td><s:textfield id="actionName" name="action" /> <s:textfield
							id="entityId" name="id" /></td>
				</tr>
			</table>
			<!-- Hidden fields - End -->
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>
