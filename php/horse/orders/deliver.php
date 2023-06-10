<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$hID = $_POST['hID'];

$sql = "SELECT users.fullname, users.phone, shop.fullname, shop.phone, shop.address, orders.location, orders.notes, orders.total_price".
" FROM users,shop,orders WHERE users.uID = orders.uID AND shop.sID = orders.sID AND orders.hID = '$hID' AND orders.state != 4 AND orders.state != 5  ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $row = $result->fetch_row();
   foreach($row as $value){
      echo $value.",";
   }
   echo "&&";

   $sql = "SELECT oID FROM orders WHERE hID = '$hID' AND state != 4 AND state != 5";
   $result = $connectNow->query($sql);
   $row = $result->fetch_assoc();
   $oID = $row['oID'];

   $sql = "SELECT food.foodname, byorder.amount FROM food,byorder WHERE byorder.oID = '$oID' AND food.fID = byorder.fID ";
   $result = $connectNow->query($sql);
   while ($row = $result->fetch_assoc()) {
      echo $row['foodname']." x ".$row['amount'].",";
   }
} else echo "No Order";

