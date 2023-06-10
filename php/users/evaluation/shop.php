<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$star = $_POST['star'];
$oID = $_POST['oID'];

$sql = "SELECT sID FROM orders WHERE oID = '$oID' ";
$result = $connectNow->query($sql);
$row = mysqli_fetch_assoc($result);
$sID = $row['sID'];
$sql = "SELECT score, number FROM shop_evaluation WHERE sID = '$sID' ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) == 0) {
        $sql = "INSERT INTO shop_evaluation SET sID = '$sID', score = '$star', number = '1' ";
        $result = $connectNow->query($sql);
        $score = $star;
        $number = 1;
}
else {
   $row = mysqli_fetch_assoc($result);
   $score = $row['score'] + $star;
   $number = $row['number'] + 1;
   $sql = "UPDATE shop_evaluation SET score = '$score', number = '$number' WHERE sID = '$sID' ";
   $result = $connectNow->query($sql);
}
$average = round($score/$number,1);
$sql = "UPDATE shop_evaluation SET average = '$average' ";
$result = $connectNow->query($sql);
$sql = "UPDATE orders SET state = 5 WHERE oID = '$oID' ";
$result = $connectNow->query($sql);
?>
