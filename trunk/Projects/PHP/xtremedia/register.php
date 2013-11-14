<?php
if ($_POST['reg']) {
	$warn = '';
	$name = m_htmlchars(stripslashes(trim(urldecode($_POST['name']))));
	$pwd = md5(stripslashes(urldecode($_POST['pwd'])));
	$email = stripslashes(trim(urldecode($_POST['email'])));
	$sec_num = $_POST['sec_num'];
	$sex = ($_POST['sex'])?$_POST['sex']:1;
	//doc chuoi ngau nhien
	$f = fopen("verify.txt", "r");
	$f1 = fread($f, 6);
	
	if ($f1 != $sec_num) $warn .= "Xin l&#7895;i! Mã b&#7843;o m&#7853;t b&#7841;n nh&#7853;p vào không &#273;úng!<br>";
	fclose($f);
	if ($mysql->num_rows($mysql->query("SELECT user_id FROM ".$tb_prefix."user WHERE user_name = '".$name."'"))) $warn .= "Xin l&#7895;i b&#7841;n! Tài kho&#7843;n này &#273;ã có ng&#432;&#7901;i s&#7917; d&#7909;ng!<br>";
	
	if (!m_check_email($email)) $warn .= "Email không h&#7907;p l&#7879;! Hãy nh&#7853;p l&#7841;i email m&#7899;i!<br>";
	elseif ($mysql->num_rows($mysql->query("SELECT user_id FROM ".$tb_prefix."user WHERE user_email = '".$email."'"))) $warn .= "Xin l&#7895;i b&#7841;n! Email này &#273;ã có ng&#432;&#7901;i s&#7917; d&#7909;ng!<br>";
	
	if ($warn) echo "<b>Có l&#7895;i x&#7843;y ra</b>: <br>".$warn;
	else {
		$playlist_id = m_random_str(20);
		$date = date("d/m/Y");
		$mysql->query("INSERT INTO ".$tb_prefix."user (user_name,user_password,user_email,user_sex,user_regdate,user_level,user_playlist_id) VALUES ('".$name."','".$pwd."','".$email."','".$sex."','".$date."','1','".$playlist_id."')");
	}
	exit();	
}
$main = $tpl->get_tpl('register');
$tpl->parse_tpl($main);
?>