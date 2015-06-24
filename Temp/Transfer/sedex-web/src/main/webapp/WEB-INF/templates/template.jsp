<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="pageTitle"
		defaultValue="Sedex - App Exchange" /></title>
</head>
<body>
	<div class="page">
		<table style="width: 100%">
			<tr>
				<td colspan="2" align="center"><tiles:insertAttribute
						name="header" /></td>
			</tr>
			<tr>
				<td align="left" width="20%"><tiles:insertAttribute name="menu" /></td>
				<td align="left">
					<div class="content">
						<tiles:insertAttribute name="body" defaultValue="Body Content" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><tiles:insertAttribute
						name="footer" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
