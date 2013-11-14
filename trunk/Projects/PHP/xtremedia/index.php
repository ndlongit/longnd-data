<?php
/*====================================*\
|| ################################## ||
|| # XtreMedia                   	# ||
|| # ------------------------------	# ||
|| # Coded by REDPHOENIX89			# ||
|| # Y!m : RedPhoenix89				# ||
|| ################################## ||
\*====================================*/
define('IN_MEDIA',true);

include('antidos.php');
include('includes/config.php');
include('includes/functions.php');
include('includes/class_template.php');

$tpl = new Template;

$isLoggedIn = m_checkLogin();

if ($_POST['reg']) {
	include('register.php');
	exit();
}
elseif ($_POST['request']) {
    include('request.php');
    exit();
}
elseif ($_POST['help']) {
    include('help.php');
    exit();
}
elseif ($_POST['login']) {
	include('login.php');
	exit();
}
elseif ($_POST['change_info'] && $isLoggedIn) {
	include('user.php');
	exit();
}
elseif ($_POST['forgot'] && $_POST['email']) {
	include('user.php');
	exit();
}
elseif ($_POST['reloadPlaylist']) {
	include('playlist.php');
	exit();
}

$value = array();
if ($url) {
	$value = split(',',$url);
	if (($value[0] != 'Download') && $_GET['url']) exit();
}
else {
	// ONLINE
	if (!$isLoggedIn) {
		$num = $mysql->num_rows($mysql->query("SELECT sid FROM ".$tb_prefix."online WHERE sid = '".SID."'"));
		if ($num == 0) $mysql->query("INSERT INTO ".$tb_prefix."online (timestamp, sid, ip) VALUES ('".NOW."', '".SID."', '".IP."')");
		else $mysql->query("UPDATE ".$tb_prefix."online SET timestamp='".NOW."',ip='".IP."' WHERE sid='".SID."'");
	}
	// TEMPLATE
	if (!$_COOKIE['MEDIA_TPL'] || $reset_tpl) {
		$default_tpl = m_get_config('default_tpl');
		m_setcookie('MEDIA_TPL', $default_tpl);
		$_COOKIE['MEDIA_TPL'] = $default_tpl;
		if ($reset_tpl) m_refresh();
	}
	if ($change_tpl) {
		if ($tpl_name)
			m_setcookie('MEDIA_TPL', $tpl_name);
		m_refresh();
	}
	$month = date('m',NOW);
	$current_month = m_get_config('current_month');
	if ($month != $current_month) {
		$mysql->query("UPDATE ".$tb_prefix."data SET m_viewed_month = 0, m_downloaded_month = 0");
		$mysql->query("UPDATE ".$tb_prefix."config SET config_value = ".$month." WHERE config_name = 'current_month'");
	}
}

if ($value[0] == 'Broken' && is_numeric($value[1])) {
	$id = (int)$value[1];
	$mysql->query("UPDATE ".$tb_prefix."data SET m_is_broken = 1 WHERE m_id = ".$id);
	die(1);
}

if (($value[0] == 'Download') && is_numeric($value[1]) && $value[2]) {
	if (!$isLoggedIn && m_get_config('must_login_to_download')) {
		header("Content-Disposition: attachment; filename = Ban_chua_dang_nhap.txt");
		echo "Ban can dang nhap moi co the download duoc nhac ve may";
		exit();
	}
	if ($value[2] == m_encode($value[1])) {
		$r = $mysql->fetch_array($mysql->query("SELECT m_url, m_is_local FROM ".$tb_prefix."data WHERE m_id = '".$value[1]."'"));
		if ($r) {
			$mysql->query("UPDATE ".$tb_prefix."data SET m_downloaded = m_downloaded + 1, m_downloaded_month = m_downloaded_month + 1 WHERE m_id = '".$value[1]."'");
			$url = ($r['m_is_local'])?$mediaFolder.'/'.$r['m_url']:$r['m_url'];
			
			header("Location: ".$url);
		}
		exit();
	}
	else { die('Mr.Hoian - Mr.Hoian@yahoo.com'); }
}

// -------------------
if (!in_array($value[0],array('Register','Forgot_Password','Change_Info'))) {
	$current_page =& $_SESSION['current_page'];
	if ($current_page != $url) $_SESSION['last_page'] = $current_page;
	$current_page = $url;
}

$_SESSION['current_tpl'] = $_COOKIE['MEDIA_TPL'];

if (!$url) {
	$html = $tpl->get_tpl('main');
	$html = $tpl->assign_blocks_content($html,array(
			'js'		=>	"<script src='js/media.js'></script><script src='js/him.js'></script>",
		)
	);
	$tpl->parse_tpl($html);
	exit();
}

if (in_array($value[0],array('List','Home','Top_Download','Top_Play')))
	include('list.php');
elseif (in_array($value[0],array('Request')))
    include('request.php');
elseif (in_array($value[0],array('Help')))
    include('help.php');
elseif (in_array($value[0],array('Singer','Play_Singer')))
	include('singer.php');
elseif ($value[0] == 'Search' || $value[0] == 'Quick_Search')
	include('search.php');
elseif (in_array($value[0],array('Album','Play_Album','List_Album')))
	include('album.php');
elseif ($value[0] == 'Play_Playlist')
	include('playlist.php');
elseif ($value[0] == 'Register')
	include('register.php');
elseif ($value[0] == 'Login')
	include('login.php');
elseif ($value[0] == 'Logout')
	include('logout.php');
elseif ($value[0] == 'Gift')
	include('gift_receive.php');
elseif (in_array($value[0],array('User','List_User','Change_Info','Forgot_Password')))
	include('user.php');
elseif ($value[0] == 'Play' && is_numeric($value[1])) {
	if (!$isLoggedIn && m_get_config('must_login_to_play')) {
		die("<b><center>Bạn cần đăng nhập mới có thể nghe được nhạc</center></b>");
	}
	$q = $mysql->query("SELECT * FROM ".$tb_prefix."data WHERE m_id = '$value[1]'");
	if (!$mysql->num_rows($q)) {
		die("<center><b>Xin lỗi bạn! Bài hát này không có thật.</b></center>");
	}
	$r = $mysql->fetch_array($q);
	$mysql->query("UPDATE ".$tb_prefix."data SET m_viewed = m_viewed + 1, m_viewed_month = m_viewed_month + 1 WHERE m_id = '$value[1]'");
	m_play($r);
}
?>