<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		
	</head>
	<body>
	    <s:url action="list" id="descrsUrl"/>

        <s:form action="save" validate="false">
			    <s:textfield id="id" name="person.id" cssStyle="display:none"/>
				<s:textfield id="firstName" label="Fisrt Name" name="person.firstName"/>
				<s:textfield id="lastName" label="Last Name" name="person.lastName"/>
				<s:submit targets="persons" notifyTopics="/save"/>
		</s:form>
	</body>
</html>
