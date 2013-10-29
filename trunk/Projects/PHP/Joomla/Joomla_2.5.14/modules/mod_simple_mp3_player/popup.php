<?php
// Modulename: "SIMPLE MP3 PLAYER" for Joomla! 1.6 + 1.7
// Version: 1.7.1
// File: popup.php
// Copyright 2009-2011: medienstroeme| web development
// Online: www.medienstroeme.de
// License:	GNU/GPL, Creative Commons BY SA, MPL 1.1
// Last update: 30.09.2011

$smp3p_path = $_GET[ 'smp3p_path' ];
$smp3p_files = $_GET[ 'smp3p_files' ];
$smp3p_titles = $_GET[ 'smp3p_titles' ];
$smp3p_width = $_GET[ 'smp3p_width' ];
$smp3p_height = $_GET[ 'smp3p_height' ];
$smp3p_showinfo = $_GET[ 'smp3p_showinfo' ];
$smp3p_showvolume = $_GET[ 'smp3p_showvolume' ];
$smp3p_volume = $_GET[ 'smp3p_volume' ];
$smp3p_volumewidth = $_GET[ 'smp3p_volumewidth' ];
$smp3p_volumeheight = $_GET[ 'smp3p_volumeheight' ];
$smp3p_autoplay = $_GET[ 'smp3p_autoplay' ];
$smp3p_loop = $_GET[ 'smp3p_loop' ];
$smp3p_shuffle = $_GET[ 'smp3p_shuffle' ];
$smp3p_showloading = $_GET[ 'smp3p_showloading' ];
$smp3p_loadingbarcolor = $_GET[ 'smp3p_loadingbarcolor' ];
$smp3p_showlist = $_GET[ 'smp3p_showlist' ];
$smp3p_showplaylistnumbers = $_GET[ 'smp3p_showplaylistnumbers' ];
$smp3p_playlistcolor = $_GET[ 'smp3p_playlistcolor' ];
$smp3p_playlistalphacolor = $_GET[ 'smp3p_playlistalphacolor' ];
$smp3p_showslider = $_GET[ 'smp3p_showslider' ];
$smp3p_sliderwidth = $_GET[ 'smp3p_sliderwidth' ];
$smp3p_sliderheight = $_GET[ 'smp3p_sliderheight' ];
$smp3p_slidercolor1 = $_GET[ 'smp3p_slidercolor1' ];
$smp3p_sliderovercolor = $_GET[ 'smp3p_sliderovercolor' ];
$smp3p_bgimage = $_GET[ 'smp3p_bgimage' ];
$smp3p_bgcolor = $_GET[ 'smp3p_bgcolor' ];
$smp3p_bgcolor1 = $_GET[ 'smp3p_bgcolor1' ];
$smp3p_bgcolor2 = $_GET[ 'smp3p_bgcolor2' ];
$smp3p_textcolor = $_GET[ 'smp3p_textcolor' ];
$smp3p_currentmp3color = $_GET[ 'smp3p_currentmp3color' ];
$smp3p_buttonwidth = $_GET[ 'smp3p_buttonwidth' ];
$smp3p_buttoncolor = $_GET[ 'smp3p_buttoncolor' ];
$smp3p_buttonovercolor = $_GET[ 'smp3p_buttonovercolor' ];
$smp3p_scrollbarcolor = $_GET[ 'smp3p_scrollbarcolor' ];
$smp3p_scrollbarovercolor = $_GET[ 'smp3p_scrollbarovercolor' ];
// New since Version 1.5.3
$smp3p_moveto_left = $_GET[ 'smp3p_moveto_left' ];
$smp3p_moveto_top = $_GET[ 'smp3p_moveto_top' ];
$smp3p_slidercolor2 = $_GET[ 'smp3p_slidercolor2' ];
$smp3p_useplaylist = $_GET[ 'smp3p_useplaylist' ];
$smp3p_playlistpath = $_GET[ 'smp3p_playlistpath' ];
// New since Version 1.7.0
$smp3p_player_design = $_GET[ 'smp3p_player_design' ];
//
$smp3p_files=str_replace("mp3|","mp3|../../",$smp3p_files);
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Simple MP3 Player</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style type="text/css">
	body {
	margin:0;
	padding:0;
	background:#<?php echo $smp3p_bgcolor2; ?>;
	}
	div {
	overflow:hidden;
	margin:0;
	padding:0;
	width:<?php echo $smp3p_width; ?>px;
	height:<?php echo $smp3p_height; ?>px;
	}
</style>
</head>
<body>
	<div>
	<!--[if !IE]> -->
	<object type="application/x-shockwave-flash" data="flashplayers/<?php echo $smp3p_player_design; ?>" width="<?php echo $smp3p_width; ?>" height="<?php echo $smp3p_height; ?>">
	<!-- <![endif]-->
	<!--[if IE]>
	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="<?php echo $smp3p_width; ?>" height="<?php echo $smp3p_height; ?>">
	<param name="movie" value="flashplayers/<?php echo $smp3p_player_design; ?>" />
	<!-->
	<param name="movie" value="flashplayers/<?php echo $smp3p_player_design; ?>" />
	<param name="bgcolor" value="#<?php echo $smp3p_bgcolor; ?>" />
	<param name="FlashVars" value="<?php if ($smp3p_useplaylist == 1) : ?>playlist=<?php echo $smp3p_playlistpath; ?><?php else : ?>mp3=../../<?php echo $smp3p_path; ?><?php echo $smp3p_files; ?>&amp;title=<?php echo $smp3p_titles; ?><?php endif; ?>&amp;width=<?php echo $smp3p_width; ?>&amp;height=<?php echo $smp3p_height; ?>&amp;showinfo=<?php echo $smp3p_showinfo; ?>&amp;showvolume=<?php echo $smp3p_showvolume; ?>&amp;volume=<?php echo $smp3p_volume; ?>&amp;volumewidth=<?php echo $smp3p_volumewidth; ?>&amp;volumeheight=<?php echo $smp3p_volumeheight; ?>&amp;autoplay=<?php echo $smp3p_autoplay; ?>&amp;loop=<?php echo $smp3p_loop; ?>&amp;shuffle=<?php echo $smp3p_shuffle; ?>&amp;showloading=<?php echo $smp3p_showloading; ?>&amp;loadingcolor=<?php echo $smp3p_loadingbarcolor; ?>&amp;showlist=<?php echo $smp3p_showlist; ?>&amp;showplaylistnumbers=<?php echo $smp3p_showplaylistnumbers; ?>&amp;playlistcolor=<?php echo $smp3p_playlistcolor; ?>&amp;playlistalpha=<?php echo $smp3p_playlistalphacolor; ?>&amp;showslider=<?php echo $smp3p_showslider; ?>&amp;sliderwidth=<?php echo $smp3p_sliderwidth; ?>&amp;sliderheight=<?php echo $smp3p_sliderheight; ?>&amp;slidercolor1=<?php echo $smp3p_slidercolor1; ?>&amp;slidercolor2=<?php echo $smp3p_slidercolor2; ?>&amp;sliderovercolor=<?php echo $smp3p_sliderovercolor; ?>&amp;bgcolor=transparent&amp;bgcolor1=<?php echo $smp3p_bgcolor1; ?>&amp;bgcolor2=<?php echo $smp3p_bgcolor2; ?>&amp;textcolor=<?php echo $smp3p_textcolor; ?>&amp;currentmp3color=<?php echo $smp3p_currentmp3color; ?>&amp;buttonwidth=<?php echo $smp3p_buttonwidth; ?>&amp;buttoncolor=<?php echo $smp3p_buttoncolor; ?>&amp;buttonovercolor=<?php echo $smp3p_buttonovercolor; ?>&amp;scrollbarcolor=<?php echo $smp3p_scrollbarcolor; ?>&amp;scrollbarovercolor=<?php echo $smp3p_scrollbarovercolor; ?><?php if ($smp3p_bgimage == -1) : ?><?php else : ?>&amp;skin=../../modules/mod_simple_mp3_player/backgrounds/<?php echo $smp3p_bgimage; ?><?php endif; ?>" />
	</object>
	<!-- <![endif]-->
	</div>
</body>
</html>