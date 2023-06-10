<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$address = $_POST['address'];

$sql = "SELECT sID FROM shop WHERE address = '$address' ";
$result = $connectNow->query($sql);
$row = $result->fetch_assoc();
$sID = $row['sID'];

$sql = "SELECT oID, location FROM orders WHERE sID = '$sID' AND hID IS NULL ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   while ($row = $result->fetch_assoc()) {
      echo $row['oID'],",";
      echo $row['location'];
      echo "&&";
   }
} else echo "No Order";

