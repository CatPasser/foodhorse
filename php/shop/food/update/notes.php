<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];
$foodname = $_POST['foodname'];
$newnotes = $_POST['newnotes'];

if($newnotes == null) {
   $newnotes = "";
}
$sql = "UPDATE food SET notes = '$newnotes', updated_at = NOW() WHERE sID = '$sID' AND foodname = '$foodname' ";
$result = $connectNow->query($sql);

?>
