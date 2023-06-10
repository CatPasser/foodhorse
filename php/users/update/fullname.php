<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$username = $_POST['username'];
$fullname = $_POST['fullname'];

if($fullname != null) {
    $sql = "UPDATE users SET fullname = '$fullname', updated_at = NOW() WHERE username = '$username' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    echo "Update Success";
} else echo "no data\n";    

?>
