<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];

$sql = "SELECT foodname,tag,photo,price,notes FROM food WHERE sID = '$sID' ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $http = true;
   echo $row['foodname'],",",$row['tag'],",",$row['photo'],",",$row['price'],",",$row['notes'];
} else $http = false;

if ($http == false){
   echo "No Data";
}
