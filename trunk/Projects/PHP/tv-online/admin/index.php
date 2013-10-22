<?php
/*====================================*\
|| ################################## ||
|| # Tivi365.net  	            	# ||
|| # ------------------------------	# ||
|| # Develop by Tivi365				# ||
|| # Nguyễn Tấn Tài 				# ||
|| # Y!m : tai_bl89					# ||
|| ################################## ||
\*====================================*/
session_start();
include('config.php');
include('func.php');
$error=$_GET['r'];
if ($_SESSION['lang_admin']=='') $_SESSION['lang_admin']=$default_lang;
$flang='../lang/'.$_SESSION['lang_admin'].'_acp.php';
if (!file_exists($flang)){ 
	$flang='../lang/'.$default_lang.'_acp.php';
	$_SESSION['lang_admin']=$default_lang;
}
include($flang);
?>
<html>
<head>
    <title>.: <?=$webtitle?>  Control Panel:.</title>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<link rel=stylesheet href="style.css" type=text/css>
</head>
<? 
if ($_SESSION['admin_login']==''){
?>
<form method="post" action="login.php">
<table width=31% align=center cellpadding=2 cellspacing=0 class=border bgcolor=white><tbody>
	<tr><td colspan=2 align=center class=title><?php if ($error=="u"){ echo $lang_acp_index['pass_err'];}else{echo $lang_acp_index['info'];}?></td></tr>
	<tr><td width=48% class=fr><?=$lang_acp_index['name_acp']?></td><td width=52% class=fr_2><input name="name" type="text" size="20"></td></tr>
	<tr><td class=fr><?=$lang_acp_index['pass_acp']?></td><td class=fr_2><input name="password" type="password" size="20"></td></tr>
    <tr><td class=fr><?=$lang_acp_index['lang_acp']?></td><td class=fr_2><select id="langbox" name="langbox"><?php 
				$listlang = file('../lang/list_lang.txt');
				foreach ($listlang as $listlang_num => $listlang_cnt) {
						if (preg_match("#(.*?)_MOTV_(.*?)#",$listlang_cnt)){
						$mylang = split('_MOTV_',trim($listlang_cnt));
					echo'<option value="'.$mylang[0].'" '.(($_SESSION['lang_admin']==$mylang[0])?' selected':'').'>'.$mylang[1].'</option>';
					}
				}?>
                        </select></td></tr>
      	<tr><td class=fr colspan=2 align=center><input class="submit" type="submit" name="submit" value="<?=$lang_acp_index['but_login']?>"></td></tr>
</tbody></table>
</form>
<?
exit();
}else{
?>
<body style="min-width:750px"><table style="width:100%;height:20%"><tbody><tr>
<td><div>
<table style="width:100%"><tbody><tr><td class=title><b><?=$lang_acp_index['tab']?></b></td><td class=title><b><?=$lang_acp_index['ch']?></b></td><td class=title><b><?=$lang_acp_index['lang_man']?></b></td><td class=title><b><?=$lang_acp_index['config']?></b></td></tr>
<tr><td>
    <div>
    <table cellpadding=2 cellspacing=0 width=100% class=border style='margin-bottom:5'><tbody>
    <tr><td class="fr_2"><a href="tab.php?act=edit" target="frame_content"><?=$lang_acp_index['tab_now']?></a></td></tr><tr><td class="fr_2"><a href="tab.php?act=add" target="frame_content"><?=$lang_acp_index['tab_edit']?></a></td></tr><tr><td class="fr_2"><a href="tab.php?act=creat" target="frame_content"><?=$lang_acp_index['tab_creat_file']?></a></td></tr><tr><td class="fr_2"><a href="tab.php?act=del" target="frame_content"><?=$lang_acp_index['tab_del_file']?></a></td></tr></tbody></table>
    </div>
</td><td>
    <div>
    <table cellpadding=2 cellspacing=0 width=100% class=border style='margin-bottom:5'><tbody>
    <tr><td class="fr_2"><a href="channel.php?act=edit" target="frame_content"><?=$lang_acp_index['ch_now']?></a></td></tr><tr><td class="fr_2"><a href="channel.php?act=add" target="frame_content"><?=$lang_acp_index['ch_edit']?></a></td></tr><tr><td class="fr_2"><a href="channel.php?act=error" target="frame_content"><?=$lang_acp_index['ch_err']?></a></td></tr><tr><td class="fr_2"><a href="channel.php?act=request" target="frame_content"><?=$lang_acp_index['ch_add']?></a></td></tr></tbody></table></div>
</td><td>
    <div>
    <table cellpadding=2 cellspacing=0 width=100% class=border style='margin-bottom:5'><tbody>
    <tr><td class="fr_2"><a href="lang.php?act=site" target="frame_content"><?=$lang_acp_index['add_lang_site']?></a></td></tr>
    <tr><td class="fr_2"><a href="lang.php?act=tab" target="frame_content"><?=$lang_acp_index['add_lang_tab']?></a></td></tr>
    </tbody></table></div>
</td><td>
    <div>
    <table cellpadding=2 cellspacing=0 width=100% class=border style='margin-bottom:5'><tbody>
    <tr><td class="fr_2"><a href="cauhinh.php?act=config" target="frame_content"><?=$lang_acp_index['config_site']?></a></td></tr>
    <tr><td class="fr_2"><a href="cauhinh.php?act=annonun" target="frame_content"><?=$lang_acp_index['announ']?></a></td></tr>
    <tr><td class="fr_2"><a href="logout.php"><?=$lang_acp_index['logout']?></a></td></tr></tbody></table></div>
</td><td>
    <div>
        <table cellpadding=2 cellspacing=0 width=100% class=border style='margin-bottom:5'><tbody>
        </tbody></table></div>
        
</td></tr></tbody></table>
</div></tr></tbody></table>
<iframe name="frame_content" id="frame_content" marginwidth="0" marginheight="0" src="" bottommargin="0" rightmargin="0" leftmargin="0" topmargin="0" noresize frameborder="0"  style="width:100%;height:80%"></iframe>
</body>
<?php
}
?>
</html>