<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];

//顯示標籤列表
$sql = "SELECT tag FROM food WHERE sID = '$sID' GROUP BY tag ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $http = true;
   while ($row = $result->fetch_assoc()) {
      echo $row['tag'],",";
   }
} else $http = false;

if ($http == false){
   echo "No Data";
}
