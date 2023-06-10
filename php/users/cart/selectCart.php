<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$uID = $_POST['uID'];

$sql = "SELECT cart.cID, cart.uID, cart.fID, cart.amount, food.foodname, food.photo, food.price, food.sID FROM cart,food WHERE cart.uID = '$uID' AND food.fID = cart.fID";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) > 0){
   $http = true;
   while ($row = $result->fetch_assoc()) {
      echo $row['foodname'],",";
      echo $row['photo'],",",$row['price'],",",$row['amount'],",",$row['cID'],",",$row['sID'];
      echo "&&";
   }
} else $http = false;

if ($http == false){
   echo "No Data";
}
