<?php
session_start();
include('admin/config.php');
$tab = $_GET['tab'];
$slng = $_GET['lng'];
$sch = $_GET['ch'];
$linktab = $link_folder.'/'.$tab.'.txt';
$flang='lang/'.$_SESSION['lang'].'_index.php';
if (!file_exists($flang)){ 
	$flang='lang/'.$default_lang.'_index.php';
	$_SESSION['lang']=$default_lang;
}
include($flang);
if (($tab!="") && ($slng=="") && ($sch=="")){
//Show channel list in this tab selected.
	if (file_exists($linktab)){
		$channel = file($linktab);
		$script .="kgttabtv('tab_".$tab."');";
		echo "<p align='center'>".$lang_index['tabchannel']."</p>";
		foreach ($channel as $channel_num => $channel_ctn) {
			if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
				$solutionch = split('_MOTV_',trim($channel_ctn));
				echo '<button onclick="window.location=\'?tab='.$tab.'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button>';
				}
				
			if (end($channel)) {
								}
			}
	}else echo '<div align="center"><strong>'.$lang_index['tabno'].'</strong></div>';
}
//Search Channel By Languages
else if (($tab=="") && ($slng!="") && ($sch=="")){
	$valuesearch=$slng;
	$list_channel="";
	$listvn = file($link_folder.'/vn_tab.txt');
	foreach ($listvn as $listvn_num => $listvn_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
			$solution = split('_MOTV_',trim($listvn_ctn));
			$linktab = $link_folder.'/'.$solution[0].'.txt';
			$channel = file($linktab);
			foreach ($channel as $channel_num => $channel_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
					$solutionch = split('_MOTV_',trim($channel_ctn));
					if ($solutionch[2]==$valuesearch) $list_channel.= '<button onclick="window.location=\'?tab='.$solution[0].'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button>';
				}
			}
		}
	}
	$listvn = file($link_folder.'/wd_tab.txt');
	foreach ($listvn as $listvn_num => $listvn_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
			$solution = split('_MOTV_',trim($listvn_ctn));
			$linktab = $link_folder.'/'.$solution[0].'.txt';
			$channel = file($linktab);
			foreach ($channel as $channel_num => $channel_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
					$solutionch = split('_MOTV_',trim($channel_ctn));
					if ($solutionch[2]==$valuesearch) $list_channel.='<button onclick="window.location=\'?tab='.$solution[0].'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button>';
				}
			}
		}
	}
	if ($list_channel!="") echo $list_channel; else echo '<center>'.$lang_index['no_search'].'</center>';

}
//Search Channel By Name
else if (($tab=="") && ($slng=="") && ($sch!="")){
	$valuesearch=$sch;
	$list_channel="";
	$listvn = file($link_folder.'/vn_tab.txt');
	foreach ($listvn as $listvn_num => $listvn_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
			$solution = split('_MOTV_',trim($listvn_ctn));
			$linktab = $link_folder.'/'.$solution[0].'.txt';
			$channel = file($linktab);
			foreach ($channel as $channel_num => $channel_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
					$solutionch = split('_MOTV_',trim($channel_ctn));
					if (substr_count($solutionch[0],strtolower($valuesearch)) !=0) $list_channel.='<button onclick="window.location=\'?tab='.$solution[0].'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button>';
				}
			}
		}
	}
	$listvn = file($link_folder.'/wd_tab.txt');
	foreach ($listvn as $listvn_num => $listvn_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
			$solution = split('_MOTV_',trim($listvn_ctn));
			$linktab = $link_folder.'/'.$solution[0].'.txt';
			$channel = file($linktab);
			foreach ($channel as $channel_num => $channel_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
					$solutionch = split('_MOTV_',trim($channel_ctn));
					if (substr_count($solutionch[0],strtolower($valuesearch)) !=0) $list_channel.='<button onclick="window.location=\'?tab='.$solution[0].'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button>';
				}
			}
		}
	}
	if ($list_channel!="") echo $list_channel; else echo '<center>'.$lang_index['no_search'].'</center>';
}
?>