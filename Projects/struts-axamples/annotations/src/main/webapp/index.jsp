<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
	<h1>Welcome To Struts 2!</h1>

	<p>
		<a href="<s:url value='/path1/path2/hello' />">Get your hello (value).</a>
	</p>
	<p>
		<a href="<s:url action='/path1/path2/register-input' includeContext="true" />"> Register
			for the drawing (action, relative).</a>
	</p>
	<p>
		<a href="<s:url namespace='/path1/path2' action='register-input' includeContext="true" />"> Register
			for the drawing (namespace).</a>
	</p>
</body>
</html>