<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="default.tiles">
	<tiles:putAttribute name="body">
		<form:form id="dataForm" modelAttribute="AppInfo" action="">
		App logo: <form:input path="logo" />
		App provider: <form:input path="provider" />
		App Rating: <form:input path="ratingValue" />
			<input type="submit" name="action" value="Save" />
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
