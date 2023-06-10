<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$uID = $_POST['uID'];
$sID = $_POST['sID'];
$fID = $_POST['fID'];
$user_notes = $_POST['user_notes'];
$amount = $_POST['amount'];

if($user_notes == null){
   $user_notes == "";
}

$sql="INSERT INTO cart SET uID = '$uID', sID = '$sID', fID = '$fID', user_notes = '$user_notes', amount = '$amount', created_at = NOW() ";
$result = $connectNow->query($sql);

?>
