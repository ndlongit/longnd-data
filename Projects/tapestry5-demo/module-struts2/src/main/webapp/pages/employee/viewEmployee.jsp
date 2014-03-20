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
			<s:textfield id="userId" name="id" cssClass="hidden" />
			<table border="0">
				<tr>
					<td><fieldset disabled="disabled">
							<legend>
								<b><i>Employee Information: 
							</legend>
							<table border="0">
								<tr>
									<td><s:textfield id="loginName" key="user.loginName"
											name="user.loginName" /> <s:textfield id="firstName"
											key="user.firstName" name="user.firstName" /> <s:textfield
											id="lastName" key="user.lastName" name="user.lastName" /> <s:textfield
											id="email" key="user.email" name="user.email" /></td>
								</tr>
							</table>
						</fieldset> <s:submit name="submit" value="Reload" align="center" /></td>
				</tr>
			</table>
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>