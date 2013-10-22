<?php
define('IN_MEDIA',true);
session_start();
unset($_SESSION['admin_login']);
session_destroy();
header("Location: ./");
?>