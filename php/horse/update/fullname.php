<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$horsename = $_POST['horsename'];
$fullname = $_POST['fullname'];

if($fullname != null) {
    $sql = "UPDATE horse SET fullname = '$fullname', updated_at = NOW() WHERE horsename = '$horsename' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    echo "Update Success";
} else echo "no data\n";    

?>
