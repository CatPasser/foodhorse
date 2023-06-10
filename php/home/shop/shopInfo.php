<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];

$sql = "SELECT shop.fullname, shop.phone, shop.address, shop.photo, shop_evaluation.average FROM shop,shop_evaluation WHERE shop.sID = '$sID' AND shop_evaluation.sID = '$sID' ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $row = $result->fetch_assoc();
   echo $row['fullname'],",",$row['phone'],",",$row['address'],",",$row['photo'],",",$row['average'];
}
else {
   $sql = "SELECT fullname, phone, address, photo FROM shop WHERE sID = '$sID' ";
   $result = $connectNow->query($sql);
   $row = $result->fetch_assoc();
   echo $row['fullname'],",",$row['phone'],",",$row['address'],",",$row['photo'],",","0";
}

