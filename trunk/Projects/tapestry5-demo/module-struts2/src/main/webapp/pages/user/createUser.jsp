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
			<%-- 			<s:fielderror fieldName="age" /> --%>
			<%-- 			<s:textfield id="age" label="Age" name="age" maxlength="20" /> --%>

			<s:fielderror fieldName="loginName" />
			<s:textfield id="loginName" key="loginName" name="loginName"
				maxlength="20" />

			<s:fielderror fieldName="firstName" />
			<s:textfield id="firstName" key="firstName" name="firstName" />
			<%-- 			<s:reset value="Reset" /> --%>
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
			<s:textfield id="actionName" name="action" /> <s:textfield
							id="id" name="id" />
			<!-- Hidden fields - End -->
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>
