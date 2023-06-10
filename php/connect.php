<?php
$serverhost = "";
$user = "";
$password = "";
$database = "";

$connectNow = new mysqli($serverhost, $user, $password, $database);
mysqli_query($connectNow,"SET NAMES 'UTF8'");
