<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$oID = $_POST['oID'];

$sql = "UPDATE orders SET state = '4'  WHERE oID = '$oID' ";
$result = $connectNow->query($sql);
