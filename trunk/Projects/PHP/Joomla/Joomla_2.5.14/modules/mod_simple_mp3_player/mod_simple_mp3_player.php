<?php
// Modulename: "SIMPLE MP3 PLAYER" for Joomla! 1.6 + 1.7
// Version: 1.7.1
// File: mod_simple_mp3_player.php
// Copyright 2009-2011: medienstroeme| web development
// Online: www.medienstroeme.de
// License:	GNU/GPL, Creative Commons BY SA, MPL 1.1
// Last update: 30.09.2011

// no direct access
defined('_JEXEC') or die;

$smp3p_path = $params->get('smp3p_path');
$smp3p_files = $params->get('smp3p_files');
$smp3p_titles = $params->get('smp3p_titles');
$smp3p_width = $params->get('smp3p_width');
$smp3p_height = $params->get('smp3p_height');
$smp3p_showinfo = $params->get('smp3p_showinfo');
$smp3p_showvolume = $params->get('smp3p_showvolume');
$smp3p_volume = $params->get('smp3p_volume');
$smp3p_volumewidth = $params->get('smp3p_volumewidth');
$smp3p_volumeheight = $params->get('smp3p_volumeheight');
$smp3p_autoplay = $params->get('smp3p_autoplay');
$smp3p_loop = $params->get('smp3p_loop');
$smp3p_shuffle = $params->get('smp3p_shuffle');
$smp3p_showloading = $params->get('smp3p_showloading');
$smp3p_loadingbarcolor = $params->get('smp3p_loadingbarcolor');
$smp3p_showlist = $params->get('smp3p_showlist');
$smp3p_showplaylistnumbers = $params->get('smp3p_showplaylistnumbers');
$smp3p_playlistcolor = $params->get('smp3p_playlistcolor');
$smp3p_playlistalphacolor = $params->get('smp3p_playlistalphacolor');
$smp3p_showslider = $params->get('smp3p_showslider');
$smp3p_sliderwidth = $params->get('smp3p_sliderwidth');
$smp3p_sliderheight = $params->get('smp3p_sliderheight');
$smp3p_slidercolor1 = $params->get('smp3p_slidercolor1');
$smp3p_sliderovercolor = $params->get('smp3p_sliderovercolor');
$smp3p_bgimage = $params->get('smp3p_bgimage');
$smp3p_bgcolor = $params->get('smp3p_bgcolor');
$smp3p_bgcolor1 = $params->get('smp3p_bgcolor1');
$smp3p_bgcolor2 = $params->get('smp3p_bgcolor2');
$smp3p_textcolor = $params->get('smp3p_textcolor');
$smp3p_currentmp3color = $params->get('smp3p_currentmp3color');
$smp3p_buttonwidth = $params->get('smp3p_buttonwidth');
$smp3p_buttoncolor = $params->get('smp3p_buttoncolor');
$smp3p_buttonovercolor = $params->get('smp3p_buttonovercolor');
$smp3p_scrollbarcolor = $params->get('smp3p_scrollbarcolor');
$smp3p_scrollbarovercolor = $params->get('smp3p_scrollbarovercolor');
$smp3p_files=str_replace("|","|$smp3p_path",$smp3p_files);
// New since Version 1.5.3
$smp3p_moveto_left = $params->get('smp3p_moveto_left');
$smp3p_moveto_top = $params->get('smp3p_moveto_top');
$smp3p_slidercolor2 = $params->get('smp3p_slidercolor2');
$smp3p_pubtn = $params->get('smp3p_pubtn');
$smp3p_pubtn_align = $params->get('smp3p_pubtn_align');
$smp3p_flashmode = $params->get('smp3p_flashmode');
$smp3p_copyright = $params->get('smp3p_copyright');
$smp3p_useplaylist = $params->get('smp3p_useplaylist');
$smp3p_playlistpath = $params->get('smp3p_playlistpath');
// New since Version 1.7.0
$smp3p_player_design = $params->get('smp3p_player_design');
?>

<?php if ($smp3p_copyright == 1) : ?>
<!-- Simple MP3 Player 1.7.1 by www.medienstroeme.de -->
<?php endif; ?>

<?php switch ($params->get( 'smp3p_showpopup' )) { case '1': ?>
<div style="text-align:<?php echo $smp3p_pubtn_align;?>">
<!--[if !IE]> -->
<object type="application/x-shockwave-flash" data="<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/flashplayers/<?php echo $smp3p_player_design;?>" width="<?php echo $smp3p_width;?>" height="<?php echo $smp3p_height;?>">
<!-- <![endif]-->
<!--[if IE]>
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="<?php echo $smp3p_width;?>" height="<?php echo $smp3p_height;?>">
<param name="movie" value="<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/flashplayers/<?php echo $smp3p_player_design;?>" />
<!-->
<param name="bgcolor" value="#<?php echo $smp3p_bgcolor;?>" />
<param name="wmode" value="<?php echo $smp3p_flashmode;?>" />
<param name="FlashVars" value="<?php if ($smp3p_useplaylist == 1) : ?>playlist=<?php echo $smp3p_playlistpath; ?><?php else : ?>mp3=<?php echo $smp3p_path; ?><?php echo $smp3p_files; ?>&amp;title=<?php echo $smp3p_titles; ?><?php endif; ?>&amp;width=<?php echo $smp3p_width; ?>&amp;height=<?php echo $smp3p_height; ?>&amp;showinfo=<?php echo $smp3p_showinfo; ?>&amp;showvolume=<?php echo $smp3p_showvolume; ?>&amp;volume=<?php echo $smp3p_volume; ?>&amp;volumewidth=<?php echo $smp3p_volumewidth; ?>&amp;volumeheight=<?php echo $smp3p_volumeheight; ?>&amp;autoplay=<?php echo $smp3p_autoplay; ?>&amp;loop=<?php echo $smp3p_loop; ?>&amp;shuffle=<?php echo $smp3p_shuffle; ?>&amp;showloading=<?php echo $smp3p_showloading; ?>&amp;loadingcolor=<?php echo $smp3p_loadingbarcolor; ?>&amp;showlist=<?php echo $smp3p_showlist; ?>&amp;showplaylistnumbers=<?php echo $smp3p_showplaylistnumbers; ?>&amp;playlistcolor=<?php echo $smp3p_playlistcolor; ?>&amp;playlistalpha=<?php echo $smp3p_playlistalphacolor; ?>&amp;showslider=<?php echo $smp3p_showslider; ?>&amp;sliderwidth=<?php echo $smp3p_sliderwidth; ?>&amp;sliderheight=<?php echo $smp3p_sliderheight; ?>&amp;slidercolor1=<?php echo $smp3p_slidercolor1; ?>&amp;slidercolor2=<?php echo $smp3p_slidercolor2; ?>&amp;sliderovercolor=<?php echo $smp3p_sliderovercolor; ?>&amp;bgcolor=<?php echo $smp3p_bgcolor; ?>&amp;bgcolor1=<?php echo $smp3p_bgcolor1; ?>&amp;bgcolor2=<?php echo $smp3p_bgcolor2; ?>&amp;textcolor=<?php echo $smp3p_textcolor; ?>&amp;currentmp3color=<?php echo $smp3p_currentmp3color; ?>&amp;buttonwidth=<?php echo $smp3p_buttonwidth; ?>&amp;buttoncolor=<?php echo $smp3p_buttoncolor; ?>&amp;buttonovercolor=<?php echo $smp3p_buttonovercolor; ?>&amp;scrollbarcolor=<?php echo $smp3p_scrollbarcolor; ?>&amp;scrollbarovercolor=<?php echo $smp3p_scrollbarovercolor; ?><?php if ($smp3p_bgimage == -1) : ?><?php else : ?>&amp;skin=./modules/mod_simple_mp3_player/backgrounds/<?php echo $smp3p_bgimage; ?><?php endif; ?>" />
</object>
<!-- <![endif]-->
<a style="background-color: transparent;" title="PopUp MP3 Player (New Window) - requires Javascript" onmouseover="window.status='open player';return true" onfocus="window.status='open player';return true" onmouseout="window.status=''" href="JavaScript:void(0);" onclick="window.open('<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/popup.php?smp3p_useplaylist=<?php echo $smp3p_useplaylist;?>&amp;smp3p_playlistpath=<?php echo $smp3p_playlistpath; ?>&amp;smp3p_path=<?php echo $smp3p_path;?>&amp;smp3p_files=<?php echo $smp3p_files;?>&amp;smp3p_titles=<?php echo $smp3p_titles;?>&amp;smp3p_player_design=<?php echo $smp3p_player_design;?>&amp;smp3p_width=<?php echo $smp3p_width;?>&amp;smp3p_height=<?php echo $smp3p_height;?>&amp;smp3p_showinfo=<?php echo $smp3p_showinfo;?>&amp;smp3p_showvolume=<?php echo $smp3p_showvolume;?>&amp;smp3p_volume=<?php echo $smp3p_volume;?>&amp;smp3p_volumewidth=<?php echo $smp3p_volumewidth;?>&amp;smp3p_volumeheight=<?php echo $smp3p_volumeheight;?>&amp;smp3p_autoplay=<?php echo $smp3p_autoplay;?>&amp;smp3p_loop=<?php echo $smp3p_loop;?>&amp;smp3p_shuffle=<?php echo $smp3p_shuffle;?>&amp;smp3p_showloading=<?php echo $smp3p_showloading;?>&amp;smp3p_loadingbarcolor=<?php echo $smp3p_loadingbarcolor;?>&amp;smp3p_showlist=<?php echo $smp3p_showlist;?>&amp;smp3p_showplaylistnumbers=<?php echo $smp3p_showplaylistnumbers;?>&amp;smp3p_playlistcolor=<?php echo $smp3p_playlistcolor;?>&amp;smp3p_playlistalphacolor=<?php echo $smp3p_playlistalphacolor;?>&amp;smp3p_showslider=<?php echo $smp3p_showslider;?>&amp;smp3p_sliderwidth=<?php echo $smp3p_sliderwidth;?>&amp;smp3p_sliderheight=<?php echo $smp3p_sliderheight;?>&amp;smp3p_slidercolor1=<?php echo $smp3p_slidercolor1;?>&amp;smp3p_slidercolor2=<?php echo $smp3p_slidercolor2;?>&amp;smp3p_sliderovercolor=<?php echo $smp3p_sliderovercolor;?>&amp;smp3p_bgimage=<?php echo $smp3p_bgimage;?>&amp;smp3p_bgcolor=<?php echo $smp3p_bgcolor;?>&amp;smp3p_bgcolor1=<?php echo $smp3p_bgcolor1;?>&amp;smp3p_bgcolor2=<?php echo $smp3p_bgcolor2;?>&amp;smp3p_textcolor=<?php echo $smp3p_textcolor;?>&amp;smp3p_currentmp3color=<?php echo $smp3p_currentmp3color;?>&amp;smp3p_buttonwidth=<?php echo $smp3p_buttonwidth;?>&amp;smp3p_buttoncolor=<?php echo $smp3p_buttoncolor;?>&amp;smp3p_buttonovercolor=<?php echo $smp3p_buttonovercolor;?>&amp;smp3p_scrollbarcolor=<?php echo $smp3p_scrollbarcolor;?>&amp;smp3p_scrollbarovercolor=<?php echo $smp3p_scrollbarovercolor;?>', '','left=<?php echo $smp3p_moveto_left;?>,top=<?php echo $smp3p_moveto_top;?>,menubar=0,location=0,status=0,width=<?php echo $smp3p_width;?>,height=<?php echo $smp3p_height;?>,toolbar=no,resizable=0');"><br /><img style="background-color:transparent" src="<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/buttons/<?php echo $smp3p_pubtn;?>" width="174" height="34" alt="PopUp MP3 Player (New Window)" border="0" /></a>
</div>
<?php break; case '2' : ?>
<div style="text-align: <?php echo $smp3p_pubtn_align;?>;">
<!--[if !IE]> -->
<object type="application/x-shockwave-flash" data="<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/flashplayers/<?php echo $smp3p_player_design;?>" width="<?php echo $smp3p_width;?>" height="<?php echo $smp3p_height;?>">
<!-- <![endif]-->
<!--[if IE]>
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="<?php echo $smp3p_width;?>" height="<?php echo $smp3p_height;?>">
<param name="movie" value="<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/flashplayers/<?php echo $smp3p_player_design;?>" />
<!-->
<param name="bgcolor" value="#<?php echo $smp3p_bgcolor;?>" />
<param name="wmode" value="<?php echo $smp3p_flashmode;?>" />
<param name="FlashVars" value="<?php if ($smp3p_useplaylist == 1) : ?>playlist=<?php echo $smp3p_playlistpath; ?><?php else : ?>mp3=<?php echo $smp3p_path; ?><?php echo $smp3p_files; ?>&amp;title=<?php echo $smp3p_titles; ?><?php endif; ?>&amp;width=<?php echo $smp3p_width; ?>&amp;height=<?php echo $smp3p_height; ?>&amp;showinfo=<?php echo $smp3p_showinfo; ?>&amp;showvolume=<?php echo $smp3p_showvolume; ?>&amp;volume=<?php echo $smp3p_volume; ?>&amp;volumewidth=<?php echo $smp3p_volumewidth; ?>&amp;volumeheight=<?php echo $smp3p_volumeheight; ?>&amp;autoplay=<?php echo $smp3p_autoplay; ?>&amp;loop=<?php echo $smp3p_loop; ?>&amp;shuffle=<?php echo $smp3p_shuffle; ?>&amp;showloading=<?php echo $smp3p_showloading; ?>&amp;loadingcolor=<?php echo $smp3p_loadingbarcolor; ?>&amp;showlist=<?php echo $smp3p_showlist; ?>&amp;showplaylistnumbers=<?php echo $smp3p_showplaylistnumbers; ?>&amp;playlistcolor=<?php echo $smp3p_playlistcolor; ?>&amp;playlistalpha=<?php echo $smp3p_playlistalphacolor; ?>&amp;showslider=<?php echo $smp3p_showslider; ?>&amp;sliderwidth=<?php echo $smp3p_sliderwidth; ?>&amp;sliderheight=<?php echo $smp3p_sliderheight; ?>&amp;slidercolor1=<?php echo $smp3p_slidercolor1; ?>&amp;slidercolor2=<?php echo $smp3p_slidercolor2; ?>&amp;sliderovercolor=<?php echo $smp3p_sliderovercolor; ?>&amp;bgcolor=<?php echo $smp3p_bgcolor; ?>&amp;bgcolor1=<?php echo $smp3p_bgcolor1; ?>&amp;bgcolor2=<?php echo $smp3p_bgcolor2; ?>&amp;textcolor=<?php echo $smp3p_textcolor; ?>&amp;currentmp3color=<?php echo $smp3p_currentmp3color; ?>&amp;buttonwidth=<?php echo $smp3p_buttonwidth; ?>&amp;buttoncolor=<?php echo $smp3p_buttoncolor; ?>&amp;buttonovercolor=<?php echo $smp3p_buttonovercolor; ?>&amp;scrollbarcolor=<?php echo $smp3p_scrollbarcolor; ?>&amp;scrollbarovercolor=<?php echo $smp3p_scrollbarovercolor; ?><?php if ($smp3p_bgimage == -1) : ?><?php else : ?>&amp;skin=./modules/mod_simple_mp3_player/backgrounds/<?php echo $smp3p_bgimage; ?><?php endif; ?>" />
</object>
<!-- <![endif]-->
</div>
<?php break; case '3' : ?>
<div style="text-align: <?php echo $smp3p_pubtn_align;?>;">
<a style="background-color: transparent;" title="PopUp MP3 Player (New Window) - requires Javascript" onmouseover="window.status='open player';return true" onfocus="window.status='open player';return true" onmouseout="window.status=''" href="JavaScript:void(0);" onclick="window.open('<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/popup.php?smp3p_useplaylist=<?php echo $smp3p_useplaylist;?>&amp;smp3p_playlistpath=<?php echo $smp3p_playlistpath; ?>&amp;smp3p_path=<?php echo $smp3p_path;?>&amp;smp3p_files=<?php echo $smp3p_files;?>&amp;smp3p_titles=<?php echo $smp3p_titles;?>&amp;smp3p_player_design=<?php echo $smp3p_player_design;?>&amp;smp3p_width=<?php echo $smp3p_width;?>&amp;smp3p_height=<?php echo $smp3p_height;?>&amp;smp3p_showinfo=<?php echo $smp3p_showinfo;?>&amp;smp3p_showvolume=<?php echo $smp3p_showvolume;?>&amp;smp3p_volume=<?php echo $smp3p_volume;?>&amp;smp3p_volumewidth=<?php echo $smp3p_volumewidth;?>&amp;smp3p_volumeheight=<?php echo $smp3p_volumeheight;?>&amp;smp3p_autoplay=<?php echo $smp3p_autoplay;?>&amp;smp3p_loop=<?php echo $smp3p_loop;?>&amp;smp3p_shuffle=<?php echo $smp3p_shuffle;?>&amp;smp3p_showloading=<?php echo $smp3p_showloading;?>&amp;smp3p_loadingbarcolor=<?php echo $smp3p_loadingbarcolor;?>&amp;smp3p_showlist=<?php echo $smp3p_showlist;?>&amp;smp3p_showplaylistnumbers=<?php echo $smp3p_showplaylistnumbers;?>&amp;smp3p_playlistcolor=<?php echo $smp3p_playlistcolor;?>&amp;smp3p_playlistalphacolor=<?php echo $smp3p_playlistalphacolor;?>&amp;smp3p_showslider=<?php echo $smp3p_showslider;?>&amp;smp3p_sliderwidth=<?php echo $smp3p_sliderwidth;?>&amp;smp3p_sliderheight=<?php echo $smp3p_sliderheight;?>&amp;smp3p_slidercolor1=<?php echo $smp3p_slidercolor1;?>&amp;smp3p_slidercolor2=<?php echo $smp3p_slidercolor2;?>&amp;smp3p_sliderovercolor=<?php echo $smp3p_sliderovercolor;?>&amp;smp3p_bgimage=<?php echo $smp3p_bgimage;?>&amp;smp3p_bgcolor=<?php echo $smp3p_bgcolor;?>&amp;smp3p_bgcolor1=<?php echo $smp3p_bgcolor1;?>&amp;smp3p_bgcolor2=<?php echo $smp3p_bgcolor2;?>&amp;smp3p_textcolor=<?php echo $smp3p_textcolor;?>&amp;smp3p_currentmp3color=<?php echo $smp3p_currentmp3color;?>&amp;smp3p_buttonwidth=<?php echo $smp3p_buttonwidth;?>&amp;smp3p_buttoncolor=<?php echo $smp3p_buttoncolor;?>&amp;smp3p_buttonovercolor=<?php echo $smp3p_buttonovercolor;?>&amp;smp3p_scrollbarcolor=<?php echo $smp3p_scrollbarcolor;?>&amp;smp3p_scrollbarovercolor=<?php echo $smp3p_scrollbarovercolor;?>', '','left=<?php echo $smp3p_moveto_left;?>,top=<?php echo $smp3p_moveto_top;?>,menubar=0,location=0,status=0,width=<?php echo $smp3p_width;?>,height=<?php echo $smp3p_height;?>,toolbar=no,resizable=0');"><br /><img style="background-color:transparent" src="<?php echo JURI::root(); ?>modules/mod_simple_mp3_player/buttons/<?php echo $smp3p_pubtn;?>" width="174" height="34" alt="PopUp MP3 Player (New Window)" border="0" /></a>
</div>
<?php break; } ?>