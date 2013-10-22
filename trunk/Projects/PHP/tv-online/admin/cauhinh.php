<?php
include('config.php');
session_start();
?>
<head>
    <title>Tivi Online</title>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<link rel=stylesheet href="style.css" type=text/css>
</head>
<body>
<?php
$act=$_GET['act'];
$flang='../lang/'.$_SESSION['lang_admin'].'_acp.php';
if (!file_exists($flang))$flang='../lang/'.$default_lang.'_acp.php';
include($flang);
if ($_SESSION['admin_login'] ==''){
	echo '<div align="center">Not Login - Chưa  đăng nhập</div>';
	exit();
}
if ($_POST['save']){
	$web = $_POST['webtitle'];
	$linkf = $_POST['linkfolder'];
	$admin_n = $_POST['admin'];
	$langdf = $_POST['langbox'];
	$acount = $_POST['taikhoan'];
	$pass = $_POST['pass'];
	$main = $_POST['mainurl'];
	$file1 ='config.php';
	$text='<?php
$webtitle="'.$web.'";
$link_folder="'.$linkf.'";
$admin_nick="'.$admin_n.'";
$taikhoan="'.$acount.'";
$matkhau="'.$pass.'";
$default_lang="'.$langdf.'";
$mainurl="'.$main.'";
?>';
	$lines = file($file1);
    $open1 = fopen($file1, "w");
    fwrite($open1,"$text");
    fclose($open1);
	echo '<div align="center">'.$lang_acp['updated'].'</div>';
	if ($linkf !=$link_folder){
		echo '<iframe src="../change.php?old='.$link_folder.'" style="height:0px;width:0px;display:none;></iframe>';
	}
}
else if ($act=="config"){
?>
<form method="post" action="cauhinh.php">
<table style="width:100%"><tbody><tr><td class="title" colspan="2" align="center"><?=$lang_acp_index['config'].$webtitle?></td></tr>
<tr><td class="fr_2"><?=$lang_config['name_stie']?></td><td class="fr"><input type="text" id="webtitle" name="webtitle" value="<?=$webtitle?>"/></td></tr>
<tr><td class="fr"><?=$lang_config['folder_link']?></td><td class="fr"><input type="text" id="linkfolder" name="linkfolder"  value="<?=$link_folder?>"/></td></tr>
<tr><td class="fr_2"><?=$lang_config['nick_admin']?></td><td class="fr_2"><input type="text" id="admin" name="admin" value="<?=$admin_nick?>"/></td></tr>
<tr><td class="fr_2"><?=$lang_config['lang_default']?></td><td class="fr"><select id="langbox" name="langbox"><?php $listlang = file('../lang/list_lang.txt');
				foreach ($listlang as $listlang_num => $listlang_ids) {
						if (preg_match("#(.*?)_MOTV_(.*?)#",$listlang[$listlang_num])){
						$solution = split('_MOTV_',trim($listlang[$listlang_num]));
					echo'<option value="'.$solution[0].'" '.(($default_lang==$solution[0])?' selected':'').'>'.$solution[1].'</option>';
					}
				}?></select></td></tr>
<tr><td class="fr"><?=$lang_config['acc_admin']?></td><td class="fr"><input type="text" id="taikhoan" name="taikhoan"value="<?=$taikhoan?>"/></td></tr>
<tr><td class="fr_2"><?=$lang_config['pass_admin']?></td><td class="fr"><input type="text" id="pass" name="pass" value="<?=$matkhau?>"/></td></tr>
<tr><td class="fr_2"><?=$lang_config['mainurl']?></td><td class="fr"><input type="text" id="mainurl" name="mainurl" value="<?=$mainurl?>"/></td></tr>

<tr><td colspan="2" align="center"><input class="submit" type="submit" value="<?=$lang_acp['btn_ok']?>" name="save"/></td></tr></tbody></table></form>
<?php
}else if ($act=='annonun'){
?>
<script>
function preview()
{
	document.getElementById('preview_html').innerHTML=document.getElementById('text').value;
}
</script>
<form method="post" action="create.php">		
<table style="width:100%"><tbody><tr><td class="title" align="center" colspan="2"><?=$lang_acp_index['announ']?></td></tr><tr><td  style="width:50%" class="fr_2" align="center">
        <?=$lang_acp['file_do']?><input name="name" type="text" value="<?='../no.htm'?>" readonly/><br />
		 <textarea rows="11" name="text" id="text"  style="background-color: rgb(235, 235, 235); width: 95%;height: 100%;"><?php echo file_get_contents('../no.htm')?></textarea><br /><input class="submit" type="button" onClick="preview()" value="<?=$lang_config['btn_preview']?>" /> <input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" />
		</td></tr><tr><td colspan="2" align="center"><div id="preview_html"></div></td></tbody></table>
        </form>
<?
}
?>
</body>