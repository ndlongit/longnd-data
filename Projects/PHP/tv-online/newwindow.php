<?php
session_start();
include('admin/config.php');
include('admin/func.php');
$flang='lang/'.$_SESSION['lang'].'_index.php';
if (file_exists($flang)){
	include($flang);
}else{
	include('lang/'.$default_lang.'_index.php');
}
$tab = $_GET['tab'];
$kenh = $_GET['xem'];
$type = $_GET['type'];
if ($type=="") $type=3;
$linktab = $link_folder.'/'.$tab.'.txt';
$str="";
if (!file_exists($linktab) || $kenh == ""){
		echo '<div align="center">
<strong>K&ecirc;nh kh&ocirc;ng t&#7891;n t&#7841;i trong h&#7879; th&#7889;ng ho&#7863;c &#273;&atilde; b&#7883; l&#7895;i b&#7887;! Ch&uacute;ng t&ocirc;i r&#7845;t ti&#7871;c n&#7871;u v&igrave; l&yacute; do n&agrave;y!</strong><br/></div>';
}else{
	//Filter infomation about channel.
		$kenh_id = file($linktab);
			foreach ($kenh_id as $kenh_num => $kenh_ids) {
				if (preg_match("#(.*?)_MOTV_(.*?)#",$kenh_id[$kenh_num])){
					$solutionch = split('_MOTV_',trim($kenh_id[$kenh_num]));
					if ($solutionch[0] == $kenh){
						$linkxem = $solutionch[1];
						break;
					}
				}		

			}
			if ($type==2){
				if (preg_match("#^http://vtc.com.vn/(.*?)#s", $linkxem)){
					$linkxem = str_replace('http://vtc.com.vn/playstream.swf?id','http://vtc.com.vn/view_Program.aspx?lpid',$linkxem);
					header("Location: ".$linkxem);
				}else watch_link($linkxem,"100%","100%",$channelerr,2,$tab,$kenh);
			}else if ($type==4){
				watch_link($linkxem,"100%","100%",$channelerr,2,$tab,$kenh);
			}else if ($type==3){watch_link($linkxem,"100%","100%",$channelerr,3,$tab,$kenh);}
}
?>