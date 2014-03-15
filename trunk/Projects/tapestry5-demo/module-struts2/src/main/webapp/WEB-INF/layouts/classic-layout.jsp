<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
<title><tiles:getAsString name="title" /></title>

<link href="<s:url value="/css/style.css"/>" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="<s:url value='/js/global.js'/>"></script>

<script>
	function onload() {
		window.focus();

		if (typeof ready == 'function') {
			ready();
		}
	}
</script>

</head>

<body onload="onload();">
	<table border="0" width="100%">
		<tr>
			<td colspan="2"><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr align="left" valign="top">
			<td width="17%"><tiles:insertAttribute name="menu" /></td>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td colspan="2"><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>
</body>
</html>