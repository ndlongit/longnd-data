<script>
	function onload() {
		//alert('onload');
	}
</script>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<tiles:insertDefinition name="title.default">
	<tiles:putAttribute name="body">

		<a href="j_spring_security_logout" id="logout">Logout</a>

		<s:url action="list" id="descrsUrl" />

		<s:form action="create-employee" validate="false">
			<s:textfield id="id" name="employee.id" cssStyle="display:none" />
			<s:textfield id="firstName" label="Fisrt Name"
				name="employee.firstName" />
			<s:textfield id="lastName" label="Last Name" name="employee.lastName" />
			<s:submit targets="persons" notifyTopics="/save" />
		</s:form>
	</tiles:putAttribute>

</tiles:insertDefinition>