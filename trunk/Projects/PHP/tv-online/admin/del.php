<?php
include('config.php');
session_start();
$flang='../lang/'.$_SESSION['lang_admin'].'_acp.php';
if (file_exists($flang)){
	include($flang);
}else{
	include('../lang/'.$default_lang.'_acp.php');
}
if ($_SESSION['admin_login'] ==''){
	echo '<div align="center">Not Login</div>';
	exit();
}	
if (isset($_POST["submit"])) {
		$name = trim($_POST['name']);
		$name='../'.$link_folder.'/'.$name.'.txt';
		unlink($name);
		echo '<div align="center">'.$lang_acp['updated'].'</div>';
}

?>
