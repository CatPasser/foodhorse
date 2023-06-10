<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];
$foodname = $_POST['foodname'];
$tag = $_POST['tag'];

$sql = "DELETE FROM food WHERE sID = '$sID' AND foodname = '$foodname' AND tag = '$tag' ";
$result = $connectNow->query($sql);


