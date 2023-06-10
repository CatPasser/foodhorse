<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$cID = $_POST['cID'];
$data = explode(",",$cID);

$sql = "SELECT sID FROM cart WHERE cID = $data[0] ";
$result2 = $connectNow->query($sql);
if (mysqli_num_rows($result2) > 0){
   $row = $result2->fetch_assoc();
   echo $row['sID'],"&&";
}
foreach($data as $value){
   $sql = "SELECT food.photo, food.foodname, food.price, cart.amount, cart.user_notes FROM cart,food WHERE cart.cID = '$value' && cart.fID = food.fID ";
   $result = $connectNow->query($sql);
   if (mysqli_num_rows($result) > 0){
      while ($row = $result->fetch_assoc()) {
         $notes = $row['user_notes'] == null? "無" : $row['user_notes'];
         echo $row['photo'],",";
         echo $row['foodname'],",",$row['price'],",",$row['amount'],",",$notes;
         echo "&&";
      }
   }
}
?>
