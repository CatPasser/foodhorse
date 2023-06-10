<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];
$foodname = $_POST['foodname'];
$newprice = $_POST['newprice'];

if($newprice != null) {
    $sql = "UPDATE food SET price = '$newprice', updated_at = NOW() WHERE sID = '$sID' AND foodname = '$foodname' ";
    $result = $connectNow->query($sql);
    echo "Update Success";
} else echo "no data\n";

?>
