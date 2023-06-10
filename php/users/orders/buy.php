<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$uID = $_POST['uID'];
$total_price = $_POST['total_price'];
$cID = $_POST['cID'];
$sID = $_POST['sID'];
$location = $_POST['location'];
$notes = $_POST['notes'];
$data = explode(",",$cID);

$sql="INSERT INTO orders SET uID = '$uID', sID = '$sID', location = '$location', notes = '$notes', total_price = '$total_price', created_at = NOW() ";
$result = $connectNow->query($sql);
$sql="SELECT oID FROM orders WHERE uID = '$uID' ";
$result = $connectNow->query($sql);
while ($row = $result->fetch_assoc()) {
   $oID = $row['oID'];
}
foreach($data as $value){
   $sql="SELECT fID, amount FROM cart WHERE cID = '$value' ";
   $result = $connectNow->query($sql);
   while ($row = $result->fetch_assoc()) {
      $fID = $row['fID'];
      $amount = $row['amount'];
   }
   $sql="INSERT INTO byorder SET fID = '$fID', uID = '$uID', oID = '$oID', amount = '$amount' ";
   $result = $connectNow->query($sql);
   $sql="DELETE FROM cart WHERE cID = '$value' ";
   $result = $connectNow->query($sql);
}

?>
