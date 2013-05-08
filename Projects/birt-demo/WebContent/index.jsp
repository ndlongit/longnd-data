<%@ taglib uri="/birt.tld" prefix="birt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BIRT Report</title>
</head>
<body>
	<%		
		String report = request.getParameter("reportName");
	%>
	<center>
		<birt:viewer id="birtViewer"
			reportDesign='<%= report%>' pattern="frameset"
			height="800" width="1200" format="html">
		
			<birt:param name="NewParameter" value='<%="ACC-000193"%>'></birt:param>
			
		</birt:viewer>
	</center>
	
</body>
</html>

 