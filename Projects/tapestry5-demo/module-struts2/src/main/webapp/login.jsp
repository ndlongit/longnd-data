<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

</head>
<body>

	<h1>Login</h1>
	<s:form action="doLogin" validate="false">
		<s:textfield id="userName" label="User Name" />
		<s:password id="password" label="Password" />
		<s:submit />
	</s:form>
</body>
</html>
