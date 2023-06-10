<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$username = $_POST['username'];
$address = $_POST['address'];

if($address != null) {
    $sql = "UPDATE users SET address = '$address', updated_at = NOW() WHERE username = '$username' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    echo "Update Success";
} else echo "no data\n";    

?>
