<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$star = $_POST['star'];
$oID = $_POST['oID'];

$sql = "SELECT hID FROM orders WHERE oID = '$oID' ";
$result = $connectNow->query($sql);
$row = mysqli_fetch_assoc($result);
$hID = $row['hID'];
$sql = "SELECT score, number FROM horse_evaluation WHERE hID = '$hID' ";
$result = $connectNow->query($sql);
if (mysqli_num_rows($result) == 0) {
        $sql = "INSERT INTO horse_evaluation SET hID = '$hID', score = '$star', number = '1' ";
        $result = $connectNow->query($sql);
        $score = $star;
        $number = 1;
}
else {
   $row = mysqli_fetch_assoc($result);
   $score = $row['score'] + $star;
   $number = $row['number'] + 1;
   $sql = "UPDATE horse_evaluation SET score = '$score', number = '$number' WHERE hID = '$hID' ";
   $result = $connectNow->query($sql);
}
$average = round($score/$number,1);
$sql = "UPDATE horse_evaluation SET average = '$average' ";
$result = $connectNow->query($sql);

?>
