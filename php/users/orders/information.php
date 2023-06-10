<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$oID = $_POST['oID'];

$sql = "SELECT shop.phone, shop.address, orders.location, orders.notes, orders.total_price, horse.fullname, horse.phone, orders.state".
" FROM shop,orders,horse WHERE orders.oID = '$oID' AND shop.sID = orders.sID AND horse.hID = orders.hID ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $row = $result->fetch_row();
   foreach($row as $value){
      echo $value.",";
   }
   echo "&&";
}
else {
   $sql = "SELECT shop.phone, shop.address, orders.location, orders.notes, orders.total_price".
   " FROM shop,orders WHERE orders.oID = '$oID' AND shop.sID = orders.sID ";
   $result = $connectNow->query($sql);
   if (mysqli_num_rows($result) > 0){
      $row = $result->fetch_row();
      foreach($row as $value){
         echo $value.",";
      }
      echo "配對中...".","."配對中...".",","外送員配對中";
   }
   echo "&&";
}

$sql = "SELECT food.foodname, byorder.amount FROM food,byorder WHERE byorder.oID = '$oID' AND food.fID = byorder.fID ";
$result = $connectNow->query($sql);
while ($row = $result->fetch_assoc()) {
   echo $row['foodname']." x ".$row['amount'].",";
}
