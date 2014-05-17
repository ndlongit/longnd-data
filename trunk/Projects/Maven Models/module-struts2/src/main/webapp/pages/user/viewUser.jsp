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
								<b><i>User Information:</i></b>
							</legend>
							<table border="0">
								<tr>
									<td><s:textfield id="loginName" key="loginName"
											name="loginName" /> <s:textfield id="firstName"
											key="firstName" name="firstName" /> <s:textfield
											id="lastName" key="lastName" name="lastName" /> <s:textfield
											id="email" key="email" name="email" /></td>
								</tr>
							</table>
						</fieldset> <s:submit name="submit" value="Reload" align="center" /></td>
				</tr>
			</table>
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>