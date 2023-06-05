<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];

$sql = "SELECT * FROM food WHERE sID = '$sID' ORDER BY tag ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $http = true;
   while ($row = $result->fetch_assoc()) {
      echo $row['tag'],",";
      echo $row['foodname'],",",$row['photo'],",";
      echo $row['price'],",",$row['created_at'],",",json_encode($row['updated_at']);
      echo "&&";
   }
} else $http = false;

if ($http == false){
   echo "No Data";
}
