<?php
include('config.php');
session_start();
?><head>
    <title>.: Tivi365 .Net :.</title>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<link rel=stylesheet href="style.css" type=text/css>
</head>
<?php
$act=$_GET['act'];
$flang='../lang/'.$_SESSION['lang_admin'].'_acp.php';
if (!file_exists($flang)) $flang='../lang/'.$default_lang.'_acp.php';
include($flang);
if ($_SESSION['admin_login'] ==''){
	echo '<div align="center">Not Login - Chưa  đăng nhập</div>';
	exit();
}
if ($act=='tab'){
?>
<script>
function addlang()
{
	id_lang = document.getElementById('tab_name').value;
	text_lang = document.getElementById('tab_text').value;
	id_lang=id_lang.toLowerCase();
	if ((id_lang!='') && (text_lang!='')){
		document.getElementById('text').value = document.getElementById('text').value+'\n'+id_lang+'_MOTV_'+text_lang;
	}else alert('No Content!');
}
</script>
		
<table style="width:100%"><tbody><tr><td class="title" align="center" colspan="2"><?=$lang_acp_index['tab_edit']?></td></tr><tr><td style="width:50%" class="fr_2">
        <?=$lang_lang['warring']?><br />
        <?=$lang_lang['name_lang']?>: <input id="tab_name" type="text" />
        <?=$lang_lang['show_lang']?>: <input id="tab_text" type="text" />
		<input class="submit" type="button" onclick="addlang()" value="<?=$lang_lang['btn_add']?>" />
		</td><td  style="width:50%" class="fr_2" align="center">
        <form method="post" action="create.php"><?=$lang_acp['file_do']?>
        <input name="name" type="text" value="<?='../list_lang.txt'?>" readonly/><br />
		 <textarea rows="11" name="text" id="text"  style="background-color: rgb(235, 235, 235); width: 95%;height: 100%;"><?php require('../list_lang.txt')?></textarea><br /><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></form>
		</td></tr><tr><td colspan="2" align="center"></tbody></table>
<?
}
else if ($act=='site'){
?>
<script>
function addlang()
{
	id_lang = document.getElementById('tab_name').value;
	text_lang = document.getElementById('tab_text').value;
	id_lang=id_lang.toLowerCase();
	if ((id_lang!='') && (text_lang!='')){
		document.getElementById('text').value = document.getElementById('text').value+'\n'+id_lang+'_MOTV_'+text_lang;
	}else alert('No Content!');
}
</script>
		<table style="width:100%"><tbody><tr><td class="title" align="center" colspan="2"><?=$lang_acp_index['tab_edit']?></td></tr><tr><td style="width:50%" class="fr_2">
        <?=$lang_lang['warring']?><br />
        <?=$lang_lang['name_lang']?>: <input id="tab_name" type="text" />
        <?=$lang_lang['show_lang']?>: <input id="tab_text" type="text" />
		<input class="submit" type="button" onclick="addlang()" value="<?=$lang_lang['btn_add']?>" />
		</td><td  style="width:50%" class="fr_2" align="center">
        <form method="post" action="create.php"><?=$lang_acp['file_do']?>
        <input name="name" type="text" value="<?='../lang/list_lang.txt'?>" readonly/><br />
		 <textarea rows="11" name="text" id="text"  style="background-color: rgb(235, 235, 235); width: 95%;height: 100%;"><?php require('../lang/list_lang.txt')?></textarea><br /><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></form>
		</td></tr><tr><td colspan="2" align="center"></tbody></table>
<?
}
?>