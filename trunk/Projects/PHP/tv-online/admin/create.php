<?php
include("config.php");
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
}	if (isset($_POST["submit"])) {
		$name = trim($_POST['name']);
		$text = trim($_POST['text']);
		$file1 = $name;
		$open1 = fopen($file1, "w");
		fwrite($open1,"$text");
		fclose($open1);
		echo '<div align="center">'.$lang_acp['updated'].'</div>';
	}

				
?>