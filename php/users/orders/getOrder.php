<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$uID = $_POST['uID'];

$sql = "SELECT orders.oID, shop.fullname FROM orders, shop WHERE orders.uID = '$uID' AND shop.sID = orders.sID AND orders.state != 4 AND orders.state != 5";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   while ($row = $result->fetch_assoc()) {
      echo $row['oID'],",";
      echo $row['fullname'];
      echo "&&";
   }
} else echo "No Order";
