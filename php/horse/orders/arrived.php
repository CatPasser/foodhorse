<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$hID = $_POST['hID'];

$sql = "UPDATE orders SET state = '3'  WHERE hID = '$hID' AND state != 4 AND state != 5";
$result = $connectNow->query($sql);
