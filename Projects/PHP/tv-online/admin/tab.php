<?php
include('config.php');
session_start();
?><head>
    <title>.: Tivi365.Net :.</title>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<link rel=stylesheet href="style.css" type=text/css>
</head>
<?php
$act=$_GET['act'];
$type=$_GET['type'];
$tab=$_GET['tab'];
$flang='../lang/'.$_SESSION['lang_admin'].'_acp.php';
if (!file_exists($flang)) $flang='../lang/'.$default_lang.'_acp.php';
include($flang);
if ($_SESSION['admin_login'] ==''){
	echo '<div align="center">Not Login - Chưa  đăng nhập</div>';
	exit();
}
if ((($act=="edit") || ($act=="add")) && ($type =='')){
				echo '<div align="center" class="fr_2">'.$lang_channel['sel_tab'].'<br/><a href="?act='.$act.'&type=vn">'.$lang_channel['vn_tab'].'</a> ----  <a href="?act='.$act.'&type=wd" >'.$lang_channel['wd_tab'].'</a></div>';
}else if (($act=="edit") && ($type!='')){
?>
<script>
function suatab(num)
{
	document.getElementById('text').value="//<?=strtoupper($type)?> Tabs"
	for(i=1;i<=num;i++){
		id_tab = document.getElementById('tab_name_'+i).value;
		show_tab = document.getElementById('tab_text_'+i).value;
		id_tab=id_tab.toLowerCase();
		if ((id_tab!='') && (show_tab!='')) document.getElementById('text').value = document.getElementById('text').value+'\n'+id_tab+'_MOTV_'+show_tab;
	}
	document.getElementById('hidebox').style.display='';
	alert('<?=$lang_channel['alert_add']?>');
}
</script>

<table style="width:100%"><tbody><tr><td class="title" align="center"><?=$lang_channel['vn_tab']?></td></tr><tr><td align="center">
	<?php
		$tab_cat = file('../'.$link_folder.'/'.$type.'_tab.txt');
		foreach ($tab_cat as $tab_num => $tabcat_cnt) {
			if (preg_match("#(.*?)_MOTV_(.*?)#",$tabcat_cnt)){
			$tabcat1 = split('_MOTV_',trim($tabcat_cnt));
			echo '<div>'.$lang_tab['name_tab'].': <input id="tab_name_'.$tab_num.'" type="text" style="width:100px" value="'.$tabcat1[0].'"/>'.$lang_tab['show_tab'].': <input id="tab_text_'.$tab_num.'" type="text" style="width:300px" value="'.$tabcat1[1].'" />';
							}
		}
		?>
      </td></tr>
      <tr><td align="center"><button class="submit" onclick="suatab(<?=$tab_num?>)"><?=$lang_acp['update']?></button><br/><?=$lang_tab['edit_help']?></td></tr><tr><td align="center" class="fr_2">
     <div id="hidebox" style="display:none">
     <form method="post" action="create.php">
         <?=$lang_acp['file_do']?> - <input name="name" type="text" value="<?="../".$link_folder."/".$type."_tab.txt"?>" readonly/><br />
		 <textarea rows="20" name="text" id="text" style="background-color: rgb(235, 235, 235); width: 95%;" readonly="readonly"></textarea><br />
        <div align="center"><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></div>
         </form></div>
	</td></tr></tbody></table>
<?php 
}else if (($act=="add") && ($type!='')){
?>
<script>
function addtab()
{
	id_tab = document.getElementById('tab_name').value;
	text_tab = document.getElementById('tab_text').value;
	id_tab=id_tab.toLowerCase();
	if ((id_tab!='') && (text_tab!='')){
		document.getElementById('text').value = document.getElementById('text').value+'\n'+id_tab+'_MOTV_'+text_tab;
		alert('<?=$lang_channel['alert_add']?>');
	}else alert('No Content!');
}
</script>
		<table style="width:100%"><tbody><tr><td class="title" align="center" colspan="2"><?=$lang_acp_index['tab_edit']?></td></tr><tr><td style="width:50%" class="fr_2">
        <?=$lang_tab['warring']?><br />
        <?=$lang_tab['name_tab']?>: <input id="tab_name" type="text" />
        <?=$lang_tab['show_tab']?>: <input id="tab_text" type="text" />
		<input class="submit" type="button" onclick="addtab()" value="<?=$lang_tab['btn_add']?>" />
		</td><td  style="width:50%" class="fr_2" align="center">
        <form method="post" action="create.php"><?=$lang_acp['file_do']?>
        <?=$lang_tab['file_do']?><input name="name" type="text" value="<?='../'.$link_folder.'/'.$type.'_tab.txt'?>" readonly/><br />
		 <textarea rows="11" name="text" id="text"  style="background-color: rgb(235, 235, 235); width: 95%;height: 100%;" readonly="readonly"><?php require('../'.$link_folder.'/'.$type.'_tab.txt')?></textarea><br /><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></form>
		</td></tr><tr><td colspan="2" align="center"></tbody></table>
	
<?php
}else if ($act=="creat"){
	?>
		<table style="width:100%"><tbody><tr><td class="title" align="center" colspan="2"><?=$lang_tab['tab_creat_title']?></td></tr><tr><td style="width:50%" class="fr_2">
         <ul>
			  <li><?=$lang_tab['in_id_del']?></li>
			  <li><?=$lang_tab['tab_id']?></li>
			  <li><?=$lang_tab['tab_creat']?><b><?=$lang_acp['btn_ok']?></b></li>
			  </ul>
		</td><td  style="width:50%" class="fr_2" align="center">
        <form method="post" action="create.php">
		<input name="name" type="text" value="../<?=$link_folder?>/Id_tab.txt" /><br/>
		<textarea rows="0" name="text" style="background-color: rgb(235, 235, 235); width: 95%;height: 100%;" readonly="readonly">//List Channel</textarea><br /><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></form>
		</td></tr></tbody></table>
	
		<?php
}else if ($act=="del"){
	?>
		<form method="post" action="del.php" style="width:100%"><table style="width:100%"><tbody><tr><td class="title" align="center" colspan="2"><?=$lang_tab['tab_del_title']?></td></tr><tr><td style="width:50%" class="fr_2">
        <ul>
			  <li><?=$lang_tab['in_id_del']?></li>
			  <li><?=$lang_tab['tab_id']?></li>
			  <li><?=$lang_tab['tab_del']?><b><?=$lang_acp['btn_ok']?></b></li>
			  </ul>
		</td><td style="width:50%" class="fr_2" align="center">
		<input name="name" type="text" value="Id_tab" />
		</td></tr><tr><td colspan="2" align="center"><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></td></tr></tbody></table></form>
	
		<?php
	}
?>
