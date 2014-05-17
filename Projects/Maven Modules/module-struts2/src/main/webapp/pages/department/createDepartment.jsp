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

		<div id="error" class="error">
			<s:actionerror />
		</div>

		<s:form action="%{action}" validate="true">
			<s:textfield key="name" name="name" maxlength="255" />
			<s:textfield key="code" name="code" maxlength="20" />
			<s:textarea key="description" name="description"
				cssStyle="width:400px; height: 100px"></s:textarea>

			<s:if
				test="%{action==@org.java.demo.web.action.base.AbstractAction@ACTION_DO_CREATE}">
				<s:submit name="create" key="common.action.create" align="center" />
			</s:if>
			<s:elseif
				test="%{action==@org.java.demo.web.action.base.AbstractAction@ACTION_DO_COPY}">
				<s:submit name="copy"
					value='%{getText("common.action.copyItem", {"Department"})}'
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
