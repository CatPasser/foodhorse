<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sql = "SELECT fullname, address FROM shop WHERE address IS NOT NULL ORDER BY sID";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   while ($row = $result->fetch_assoc()) {
      echo $row['fullname'].",".$row['address']."&&";
   }
} else
   echo "No Data";

?>
