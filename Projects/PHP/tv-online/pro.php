<?php
include('admin/config.php');
$act=$_GET['act'];
if ($act!="get"){
	echo'<meta http-equiv="refresh" content="3;url=?act=get">
<center><img src="images/wait.gif" border=0 /><br />Load database!</center>';
}else{
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
	<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
  	<meta name="description" content="Xem tivi với chất nhanh nhất! Xem các kênh VTC, VTV, HTV, các kênh phim đặc sắc.Hỗ trợ tốt nhanh, luôn cập nhật.">
	<meta name="keywords" content="xem tivi nhanh nhat, htv, vtc, itv, nhanh nhat">
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="motivi_pro">
<style>
button {
	background : url('<?=$mainurl?>/images/btn.png') repeat-x left 0%;
	border : 1px solid #6a8da4;
	cursor : pointer;
	width:100px;
	height:25px;
	text-transform:uppercase;
	font-size:10px;
}
button:hover {
	color: #ffffff;
	background : url('<?=$mainurl?>/images/btn.png') repeat-x left 100%;
	border : 1px solid #000000;
	cursor : pointer;
	width:100px;
	height:25px;
	text-transform:uppercase;
	font-size:10px;

}
</style>
<table align="center"><tbody>
<tr><td colspan="3" align="center" id="title"><strong><?=$webtitle?> Protable</strong><br/><i>Xem tivi với chất lượng tốt nhất!</i> <a href="<?=$mainurl?>" target="_blank"><?=$mainurl?></a></td></tr>
<tr><td>
<div style="vertical-align:top;max-height:300px;height:300px;overflow:auto;width:135px"  id="channel_left">
<?php
$listvn = file($link_folder.'/vn_tab.txt');
	foreach ($listvn as $listvn_num => $listvn_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
			$solution = split('_MOTV_',trim($listvn_ctn));
			$linktab = $link_folder.'/'.$solution[0].'.txt';
			$channel = file($linktab);
			foreach ($channel as $channel_num => $channel_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
					$solutionch = split('_MOTV_',trim($channel_ctn));
					echo '<button onclick="document.getElementById(\'MPlayer\').src=\'./newwindow.php?type=4&tab='.$solution[0].'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button><br />';
				}
			}
		}
}
?>
</div>
</td><td style="width: 350px; height: 300px" align="center" ><iframe name="MPlayer" id="MPlayer" marginwidth="0" marginheight="0" src="http://tivi365.net/newwindow.php" bottommargin="0" rightmargin="0" leftmargin="0" topmargin="0" nosize="" scrolling="no"  frameborder="0" style="width: 350px; height: 300px"></iframe></td><td>
<div style="vertical-align:top;max-height:300px;height:300px;overflow:auto;width:135px" id="channel_right"><center><?php
$listvn = file($link_folder.'/wd_tab.txt');
	foreach ($listvn as $listvn_num => $listvn_ctn) {
		if (preg_match("#(.*?)_MOTV_(.*?)#",$listvn_ctn)){
			$solution = split('_MOTV_',trim($listvn_ctn));
			$linktab = $link_folder.'/'.$solution[0].'.txt';
			$channel = file($linktab);
			foreach ($channel as $channel_num => $channel_ctn) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$channel_ctn)){
					$solutionch = split('_MOTV_',trim($channel_ctn));
					echo '<button onclick="document.getElementById(\'MPlayer\').src=\'./newwindow.php?type=4&tab='.$solution[0].'&xem='.$solutionch[0].'\'">'.$solutionch[0].'</button><br />';
				}
			}
		}
}
?>
</div></td></tr></tbody></table>
</div>

</body>
<?php }?>
