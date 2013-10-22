<?php
session_start();
include('config.php');
?><head>
    <title>.: MoTivi 2009 ver 3  :.</title>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
	<link rel=stylesheet href="style.css" type=text/css>
</head>
<?php
$act=$_GET['act'];
$tab=$_GET['tab'];
$flang='../lang/'.$_SESSION['lang_admin'].'_acp.php';
if (!file_exists($flang)){ 
	$flang='../lang/'.$default_lang.'_acp.php';
	$_SESSION['lang_admin']=$default_lang;
}
include($flang);
if ($_SESSION['admin_login'] ==''){
	echo '<div align="center">Not Login - Chưa  đăng nhập</div>';
	exit();
}
//--------------------	
//BEGIN CHANNEL LIST |
//--------------------
if ((($act=="edit") || ($act=="add")) && ($tab =='')){
	?>
		
<table style="width:100%"><tbody><tr><tr><td colspan="2" align="center" class="fr_2"><?=$lang_channel['sel_tab']?></td></tr><td class="title" align="center"><?=$lang_channel['vn_tab']?></td><td class="title" align="center"><?=$lang_channel['wd_tab']?></td></tr><tr><td>
	<div><table style="width:100%"><tbody>
		<?php
		$tabvn = file('../'.$link_folder.'/vn_tab.txt');
		foreach ($tabvn as $tabvn_num => $tabvn_ctn) {
			if (preg_match("#(.*?)_MOTV_(.*?)#",$tabvn_ctn)){
			$tabvn1 = split('_MOTV_',trim($tabvn_ctn));
			echo'<tr><td class="fr_2"><a href="channel.php?&act='.$act.'&tab='.$tabvn1[0].'">'.$tabvn1[1].'</a><br/></td></tr>';
							}
		}
		?>
	</tbody></table></div></td><td>
	<div><table style="width:100%"><tbody>
	<?php
		$tabwd = file('../'.$link_folder.'/wd_tab.txt');
		foreach ($tabwd as $tabwd_num => $tabwd_ctn) {
			if (preg_match("#(.*?)_MOTV_(.*?)#",$tabwd_ctn)){
			$tabwd1 = split('_MOTV_',trim($tabwd_ctn));
			echo'<tr><td class="fr_2"><a href="channel.php?&act='.$act.'&tab='.$tabwd1[0].'">'.$tabwd1[1].'</a><br/></td></tr>';
							}
		}
		?></tbody></table></div></td></tr></tbody></table>
<?php
//--------------------	
//BEGIN EDIT CHANNEL  |
//--------------------
}else if (($act=="edit") && ($tab !='')){
	?>
<script>
function suakenh(num)
{
	document.getElementById('text').value="//<?=strtoupper($tab)?> Chanel";
	for(i=1;i<=num;i++){
		id_ch = document.getElementById('ch_name_'+i).value;
		link_ch = document.getElementById('ch_link_'+i).value;
		lang_ch = document.getElementById('ch_lang_'+i).value;
		id_ch=id_ch.toLowerCase();
		if ((id_ch!='') && (link_ch!='')) document.getElementById('text').value = document.getElementById('text').value+'\n'+id_ch+'_MOTV_'+link_ch+'_MOTV_'+lang_ch;
	}
	document.getElementById('hidebox').style.display='';
	alert('<?=$lang_channel['alert_add']?>');
}
</script>
<table style="width:100%"><tbody>
    <tr><td class="title" align="center" colspan="2"><?=$lang_channel['sel_tab_ch']?></td></tr>
    <tr><td align="left" style="width:20%"><?=$lang_channel['edit_help']?></td><td align="center" class="fr" style="width:80%">
	<?php
	$list_channel =file("../".$link_folder."/".$tab.".txt");
	foreach ($list_channel as $list_channel_num => $list_channel_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$list_channel_ctn)){
			$solution = split('_MOTV_',trim($list_channel_ctn));
			echo '<div>'.$lang_channel['name_channel'].': <input id="ch_name_'.$list_channel_num.'" type="text" style="width:100px" value="'.$solution[0].'" />
        Link: <input id="ch_link_'.$list_channel_num.'" type="text" style="width:300px" value="'.$solution[1].'" />'.$lang_channel['language'].': <select id="ch_lang_'.$list_channel_num.'">';
			$listlang = file('../list_lang.txt');
				foreach ($listlang as $listlang_num => $listlang_ctn) {
					if (preg_match("#(.*?)_MOTV_(.*?)#",$listlang_ctn)){
					$vallang = split('_MOTV_',trim($listlang_ctn));
					echo'<option value="'.$vallang[0].'" '.(($solution[2]==$vallang[0])?' selected':'').'>'.$vallang[1].'</option>';
					}
				}
			echo '</select></div>';
		}
	}
	?>
    <button class="submit" onclick="suakenh(<?=$list_channel_num?>)"><?=$lang_acp['update']?></button>
    </td></tr><tr><td align="center" class="fr_2" colspan="2">
    <div id="hidebox" style="display:none">
     <form method="post" action="create.php">
         <?=$lang_acp['file_do']?> - <input name="name" type="text" value="<?="../".$link_folder."/".$tab.".txt"?>" readonly/><br />
		 <textarea rows="20" name="text" id="text" style="background-color: rgb(235, 235, 235); width: 95%;" readonly="readonly"></textarea><br />
        <div align="center"><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></div>
         </form></div>
    </td>
    </tbody></table>
<?php
//--------------------	
//BEGIN ADD CHANNEL  |
//--------------------
}else if (($act=="add") && ($tab!='')){
		?>
<script>
function themkenh()
{
	id_ch = document.getElementById('ch_name').value;
	link_ch = document.getElementById('ch_link').value;
	lang_ch = document.getElementById('ch_lang').value;
	id_ch=id_ch.toLowerCase();
	if ((id_ch!='') && (link_ch!='')){
		document.getElementById('text').value = document.getElementById('text').value+'\n'+id_ch+'_MOTV_'+link_ch+'_MOTV_'+lang_ch;
		alert('<?=$lang_channel['alert_add']?>');
	}else alert('No link');
}
</script>
		<table style="width:100%"><tbody><tr><td class="title" align="center"><?=$lang_channel['add_title']?></td></tr>
        <tr><td style="width:30%" class="fr_2" align="center">
        
			<ul>
              <li style="display:block;">
              <?=$lang_channel['name_channel']?>: <input id="ch_name" type="text" />
              Link : <input id="ch_link" type="text" />
              <?=$lang_channel['language']?>: <select id="ch_lang"><?php 
				$list_lang = file('../list_lang.txt');
				foreach ($list_lang as $list_lang_num => $list_lang_ctn) {
					if (preg_match("#(.*?)_MOTV_(.*?)#",$list_lang_ctn)){
						$solution = split('_MOTV_',trim($list_lang_ctn));
					echo'
                    <option value="'.$solution[0].'">'.$solution[1].'</option>';
					}
				}

				?></select><br />
                <button onclick="themkenh()" class="submit"><?=$lang_channel['btn_add']?></button>
              </li>
			  <li style="display:block;font-weight:bold"><?=$lang_channel['warring']?></li>
			  </ul>
		</td></tr><tr><td  style="width:70%" class="fr_2" align="center">
        <form method="post" action="create.php">
         <?=$lang_acp['file_do']?> - <input name="name" type="text" value="<?="../".$link_folder."/".$tab.".txt"?>" readonly/><br />
		 <textarea rows="20" name="text" id="text" style="background-color: rgb(235, 235, 235); width: 95%;" readonly="readonly"><?php require("../".$link_folder.'/'.$tab.".txt");?></textarea><br />
        <div align="center"><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></div>
         </form>
		</td></tr></tbody></table>
	
	<?php
}else if (( $act=="error") ||( $act=="request")) {
		?>
	<div align="center"><form method="post" action="create.php"><table style="100%"><tbody><tr><td class="title" align="center" ><?php if ($act=="error") echo $lang_channel['channel_err'];else echo $lang_channel['channel_add'];?> : <?=$lang_acp['file_do']?> - <input name="name" type="text" value="<?php if ($act=="error") echo '../kenhloi.txt';else echo '../kenhthem.txt';?>" readonly/></td></tr><tr><td  style="width:70%" class="fr_2" align="center"><?=$lang_channel['emp_file']?> <b><?=$lang_acp['btn_ok']?></b></td></tr><tr><td  style="width:70%" class="fr_2" align="center">
		 <textarea rows="20" name="text" style="background-color: rgb(235, 235, 235); width: 95%;"><?php if ($act=="error"){require('../kenhloi.txt');}else{require('../kenhthem.txt');}?></textarea>
		</td></tr><tr><td colspan="2" align="center"><input class="submit" type="submit" name="submit" value="<?=$lang_acp['btn_ok']?>" /></td></tr></tbody></table></form></div>
<?php } ?>