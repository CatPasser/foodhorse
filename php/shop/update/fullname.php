<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$shopname = $_POST['shopname'];
$fullname = $_POST['fullname'];

if($fullname != null) {
    $sql = "UPDATE shop SET fullname = '$fullname', updated_at = NOW() WHERE shopname = '$shopname' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    echo "Update Success";
} else echo "no data\n";    

?>
