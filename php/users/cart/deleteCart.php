<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$cID = $_POST['cID'];

$sql="DELETE FROM cart WHERE cID = '$cID' ";
$result = $connectNow->query($sql);

?>
