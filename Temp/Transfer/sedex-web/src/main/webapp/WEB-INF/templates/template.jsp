<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><tiles:insertAttribute name="pageTitle"
		defaultValue="Sedex - App Exchange" /></title>

<!-- Bootsrap CSS -->
<link rel="shortcut icon" href="resources/images/fav_icon.png" />
<!-- FONT AWESOME CSS -->
<link rel="stylesheet" href="resources/library/font-awesome/css/font-awesome.min.css" />
<!-- DESKTOP NAVIGATION CSS -->
<link rel="stylesheet" type="text/css" href="resources/css/desktop-nav/animate.css" />
<link rel="stylesheet" type="text/css" href="resources/css/desktop-nav/style.css" />
<link rel='stylesheet' type='text/css' href='resources/css/desktop-nav/elusive-webfont.css' />
<!-- RATY CSS. DOCUMENT URL: https://github.com/wbotelhos/raty -->
<link rel="stylesheet" href="resources/library/raty/jquery.raty.css" />
<!-- MOBILE SEARCH -->
<link rel="stylesheet" type="text/css" href="resources/css/sbEx/component.css" />
<script src="resources/javascript/sbEx/modernizr.custom.js"></script>
<!-- MAIN CSS -->
<link rel='stylesheet' type='text/css' href='resources/css/main.css' />
		
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
