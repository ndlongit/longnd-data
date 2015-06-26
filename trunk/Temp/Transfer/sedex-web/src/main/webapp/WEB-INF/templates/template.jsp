<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><tiles:insertAttribute name="pageTitle"
		defaultValue="Sedex - App Exchange" /></title>
<!-- Bootsrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/asset/library/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/asset/library/bootstrap/css/bootstrap-theme.css" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/asset/images/fav_icon.png" />
<!-- FONT AWESOME CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/asset/library/font-awesome/css/font-awesome.min.css" />
<!-- DESKTOP NAVIGATION CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/asset/css/desktop-nav/animate.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/asset/css/desktop-nav/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/asset/css/desktop-nav/elusive-webfont.css" />
<!-- RATY CSS. DOCUMENT URL: https://github.com/wbotelhos/raty -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/asset/library/raty/jquery.raty.css" />
<!-- MOBILE SEARCH -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/asset/css/sbEx/component.css" />
<script src="<%=request.getContextPath()%>/asset/javascript/sbEx/modernizr.custom.js"></script>
<!-- MAIN CSS -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/asset/css/main.css" />
		
</head>
<body>
	<div class="page">
		<table style="width: 100%">
			<tr>
				<td colspan="2"><tiles:insertAttribute
						name="header" /></td>
			</tr>
			<tr>
				<td><tiles:insertAttribute name="menu" /></td>
				<td>
					<div class="content">
						<tiles:insertAttribute name="body" defaultValue="Body Content" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><tiles:insertAttribute name="footer" /></td>
			</tr>
		</table>
	</div>
</body>
</html>
