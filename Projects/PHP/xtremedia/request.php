<?php
if (!$isLoggedIn) 
	{
		die("<b><center>Xin lỗi! Bạn cần phải đăng nhập mới có thể gửi yêu cầu nhạc.</center></b>");
	}
else
{
function isFloodPost(){
	$_SESSION['current_message'] = time();
	global $wait_request;
	$timeDiff_request = $_SESSION['current_message'] - $_SESSION['prev_message'];

	$floodInterval_request	= 30;
	$wait_request = $floodInterval_request - $timeDiff_request ;
	
	if($timeDiff_request <= $floodInterval_request){
		return true;
	}else {
		return false;
	}
}

if ($_POST['request']) {
	session_start();
	if (isFloodPost($_SESSION['prev_request'])) {
			echo '<p align="center"><b>Xin lỗi! Bạn cần phải chờ thêm '.$wait_request.' giây nữa mới có thể gửi yêu cầu tiếp theo.</b></p>';
		
			//save it for future reference
			//$_SESSION['prev_message'] = time();
			exit();
	}

	$warn = '';
	$title_request = m_htmlchars(stripslashes(trim(urldecode($_POST['title_request']))));
	$singer_request = m_htmlchars(stripslashes(trim(urldecode($_POST['singer_request']))));
	$author_request = m_htmlchars(stripslashes(trim(urldecode($_POST['author_request']))));
	$info_request = htmlspecialchars(stripslashes(trim(urldecode($_POST['info_request']))));
	$ym_request = m_htmlchars(stripslashes(trim(urldecode($_POST['ym_request']))));
	$email = stripslashes(trim(urldecode($_POST['email_request'])));
	$ip = $_SERVER["REMOTE_ADDR"];
	$date = date("d-m-Y");
	if (!m_check_email($email)) $warn .= "Email nhập vào không hợp lệ!<br>";
	
	if ($warn) echo "<p align=\"center\"><b>Có lỗi xảy ra</b>: <br>".$warn."</p>";
	else {
		$mysql->query("INSERT INTO ".$tb_prefix."request (request_title,request_singer,request_author,request_info,request_ym,request_email,request_ip,request_date) VALUES ('".$title_request."','".$singer_request."','".$author_request."','".$info_request."','".$ym_request."','".$email."','".$ip."','".$date."')");
		echo '<p align="center"><b>Yêu cầu của bạn đã được gửi cho chúng tôi.<br /> Chúng tôi sẽ cố gắng đáp ứng sớm nhất trong thời gian có thể!</b></p>';
	}
	$_SESSION['prev_message'] = time();
	exit();
}

$html = $tpl->get_tpl('request');
$tpl->parse_tpl($html);
}
?>