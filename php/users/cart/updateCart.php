<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$cID = $_POST['cID'];
$amount = $_POST['amount'];

$sql = "UPDATE cart SET  amount = '$amount' WHERE cID = '$cID' ";
$result = $connectNow->query($sql);

?>
