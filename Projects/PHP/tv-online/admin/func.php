<?php 
include('config.php');
function m_htmlchars($str) {
	return str_replace(
		array('&', '<', '>', '"', chr(92), chr(39)),
		array('&amp;', '&lt;', '&gt;', '&quot;', '&#92;', '&#39'),
		$str
	);
}
function m_setcookie($name, $value = '', $permanent = true) {
	global $mainurl;
	$setCookieType=2;
	$expire = ($permanent)?(time() + 60 * 60 * 24 * 365):0;
	
	if ($setCookieType == 1) {
		$url = $mainurl;
		if ($url[strlen($url)-1] != '/') $url .= '/';
		$secure = (($_SERVER['HTTPS'] == 'on' OR $_SERVER['HTTPS'] == '1') ? true : false);
		$p = parse_url($url);
		$path = !empty($p['path']) ? $p['path'] : '/';
		$domain = $p['host'];
		if (substr_count($domain, '.') > 1) {
			while (substr_count($domain, '.') > 1)
			{
				$pos = strpos($domain, '.');
				$domain = substr($domain, $pos + 1);
			}
			
		}
		else $domain = '';
		@setcookie($name, $value, $expire, $path, $domain, $secure);
	}
	else @setcookie($name,$value,$expire);
}
function watch_link($link,$w,$h,$texterr,$type,$tab,$chn){
	global $webtitle,$mainurl;
	$embed = '<EMBED EnableContextMenu=\'0\' autostart=\'0\' type=\'application/x-mplayer2\' SRC=\''.$mainurl.'/newwindow.php?tab='.$tab.'&xem='.$chn.'\' pluginspage=\'http://www.microsoft.com/Windows/Downloads/Contents/Products/MediaPlayer/\' showstatusbar=\'1\' style=\'width:'.$w.' ; height:'.$h.' \'/>';
	$forum='[media]'.$mainurl.'/newwindow.php?tab='.$tab.'&xem='.$chn.'[/media]';
	$asx = '<asx Version="3.0">'.
	$asx .= '<entry>'.
		'<Banner href="'.$mainurl.'/images/logo.png"></Banner>'.
		'<Title>'.$chn.'</Title>'.
		'<Author>'.$webtitle.'</Author>'.
		'<Copyright>'.$webtitle.'</Copyright>'.
		'<Moreinfo href="'.$mainurl.'/?tab='.$tab.'&xem='.$chn.'" />'.
		'<param name="type" value="rtmp" />';
	if ((preg_match("#^mms://(.*?)#s", $link)) || (preg_match("#^rtsp://(.*?)#s", $link))){
			$player = '<EMBED EnableContextMenu="0" type="application/x-mplayer2" SRC="'.$link.'"  pluginspage="http://www.microsoft.com/Windows/Downloads/Contents/Products/MediaPlayer/" showstatusbar="1" style="width:'.$w.' ; height:'.$h.' ">';
			$asx .='<Ref href="'.$link.'" />';
	}else if (preg_match("#^http://(.*?)#s", $link)){
		if (preg_match("#^http://megafun.vn/common/v2/player/player.swf(.*?)#s", $link)){
			$player= '<embed type=\'application/x-shockwave-flash\' src=\''.$link.'\' quality=\'high\' allowfullscreen=\'true\' allowscriptaccess=\'always\' wmode=\'opaque\' style=\'width:'.$w.' ; height:'.$h.' \'/>';
			$embed=$player;
			$forum='[Flash]'.$link.'[/Flash]';
			$asx .='<Ref href="'.$link.'" />';
		}else if ((preg_match("#^http://www.htvc.vn(.*?)#s", $link)) || (preg_match("#^http://farm.vtc.vn(.*?)#s", $link))){
			$player= '<iframe src="'.$link.'" scrolling="no" frameborder="0" style="width:'.$w.' ; height:'.$h.' "></iframe>';
			$embed="No Support Embed!";
			$forum=$embed;
               }else if ((preg_match("#^http://www.vpoptv.com(.*?)#s", $link)) || (preg_match("#^http://tv24.vn.tivi365.net(.*?)#s", $link))){
			$player= '<iframe src="'.$link.'" scrolling="no" frameborder="0" style="width:'.$w.' ; height:'.$h.' "></iframe>';
			$embed="No Support Embed!";
			$forum=$embed;
                 }else if ((preg_match("#^http://tv-msn.com/(.*?)#s", $link)) || (preg_match("#^http://tvod.vn/(.*?)#s", $link))){
			$player= '<iframe src="'.$link.'" scrolling="no" frameborder="0" style="width:'.$w.' ; height:'.$h.' "></iframe>';
			$embed="No Support Embed!";
			$forum=$embed;
		}else if (preg_match("#^http://www.vpoptv.com/#s", $link)){
			$player= '<embed allowFullScreen=\'true\' src=\''.$link.'&advertisment=0&showChat=0&pwidth='.$w.'&pheight='.$h.'\' type=\'application/x-shockwave-flash\' width=\''.$w.'\' height=\''.$h.'\' allowScriptAccess=\'always\' ></embed>';
			$embed=$player;
			$forum='[Flash]'.$link.'&advertisment=0&showChat=0&pwidth='.$w.'&pheight='.$h.'[/Flash]';
     
		}else{
			$player= '<EMBED EnableContextMenu="0" type="application/x-mplayer2" showstatusbar="1" SRC="'.$link.'"  pluginspage="http://www.microsoft.com/Windows/Downloads/Contents/Products/MediaPlayer/"  style="width:'.$w.' ; height:'.$h.' ">';
			$asx .='<Ref href="'.$link.'" />';
			}
	}else if (preg_match("#^file=(.*?)#s", $link)){
			$player= '<iframe src="http://www.htvc.vn/swfs/StrobeMediaPlayback.swf?'.$link.'&autostart=true&fullscreen=true&controlbar=bottom" scrolling="no" frameborder="0" style="width:'.$w.' ; height:'.$h.' "></iframe>';
			$embed="No Support Embed!";
			$forum=$embed;
        }else if (preg_match("#^file=(.*?)#s", $link)){
			$player= '<iframe src="http://tvod.vn/sites/all/themes/pcportal_ver2/js/swplayer/StrobeMediaPlaybackDis.swf?'.$link.'&autostart=true&fullscreen=true&controlbar=bottom" scrolling="no" frameborder="0" style="width:'.$w.' ; height:'.$h.' "></iframe>';
			$embed="No Support Embed!";
			$forum=$embed;
       
	}else if (preg_match("#^file=(.*?)#s", $link)){
			$player= '<iframe src="http://farm.vtc.vn/media/vtcnews/resources/swf/flv/flvplayer.swf?'.$link.'&autostart=true&fullscreen=true&controlbar=bottom" scrolling="no" frameborder="0" style="width:'.$w.' ; height:'.$h.' "></iframe>';
			$embed="No Support Embed!";
			$forum=$embed;
	}else if (preg_match("#^rtmp://(.*?)#s", $link)  || preg_match("#^(.*?)&file=(.*?)#s", $link)){
			$player= '<embed type="application/x-shockwave-flash" src="player.swf"  quality="high" allowfullscreen="true" allowscriptaccess="always" wmode="opaque" flashvars="streamer='.$link.'&type=rtmp&fullscreen=true&skin=http://tivi365.net/skin-tivi365.zip&autostart=true&logo=images/icon-kenh.png&logo.position=top-left&logo.link=http://tivi365.net&logo.linktarget=_blank&logo.margin=34&logo.hide=false&controlbar=bottom&image=images/logo.png" style="width:'.$w.' ; height:'.$h.' "/>';
			$s=split('&file=',$link);
			$forum='[Flash]'.$mainurl.'/player.swf?file='.$mainurl.'/newwindow.php%3Ftab%3D'.$tab.'%26xem%3D'.$chn.'&autostart=false[/Flash]';
			$asx .='<Ref href="'.$s[1].'" />';
			$asx .='<param name="streamer" value="'.$s[0].'" />';
			$embed='<embed type=\'application/x-shockwave-flash\' src=\''.$mainurl.'/player.swf\' flashvars=\'file='.$mainurl.'/newwindow.php%3Ftab%3D'.$tab.'%26xem%3D'.$chn.'&autostart=false\' quality=\'high\' allowfullscreen=\'true\' allowscriptaccess=\'always\' wmode=\'opaque\' style=\'width:'.$w.' ; height:'.$h.' \'/>';
}else{
		$player= '<div align="center"><strong>'.$texterr.'</strong></div>';
		$loi=true;
	}
	$asx .="</entry></asx>";
	if ($type==1){
		echo '<div id="MPlayer">'.$player.'</div>';
	}else if ($type==2){
		echo $player;
	}else if ($type==3){
		header("Cache-Control: private");
		header("Pragma: public");
		header("Content-Type: video/x-ms-wmv; charset=utf-8");
		echo $asx;
	}
	//echo $player;
}
?>