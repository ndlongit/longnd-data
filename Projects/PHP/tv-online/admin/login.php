<?php
session_start();
include("func.php");
include("config.php");
$matkhau = stripslashes($matkhau);
if (isset($_POST["submit"])) {
	$name = m_htmlchars(trim($_POST['name']));
	$name = str_replace( '|', '&#124;', $name);
	$langid =  trim($_POST['langbox']);
	$password = stripslashes($_POST['password']);
	$_SESSION['lang_admin']=$langid;
	if (($name == $taikhoan) && ($password == $matkhau)){
		$_SESSION['admin_login'] = "admin_logined";
		header("Location: ./");
	}else header("Location: ./index.php?r=u");
}
?>