<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sql = "SELECT sID,fullname,photo,tag FROM shop WHERE address IS NOT NULL AND photo IS NOT NULL AND tag IS NOT NULL";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $http = true;
   while ($row = $result->fetch_assoc()) {
      echo $row['sID'],",";
      echo $row['fullname'],",",$row['tag'],",",$row['photo'];
      echo "&&";
   }
} else $http = false;

if ($http == false){
   echo "No Data";
}
