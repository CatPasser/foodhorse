<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$oID = $_POST['oID'];
$hID = $_POST['hID'];


$sql = "UPDATE orders SET hID = '$hID', state = '2'  WHERE oID = '$oID' AND hID IS NULL ";
$result = $connectNow->query($sql);
$sql = "SELECT hID FROM orders WHERE oID = '$oID' ";
$result = $connectNow->query($sql);
$row = $result->fetch_assoc();
if($row['hID'] != $hID)
   echo "Already Taken";
else
   echo "Success";

