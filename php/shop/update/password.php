<?php
require '/var/www/html/connect.php';  
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$shopname = $_POST['shopname'];
$password = $_POST['password'];
$newpassword = $_POST['newpassword'];
$newpassword_Hash = password_hash($newpassword, PASSWORD_DEFAULT);

if( $password != null && $newpassword != null){
    $sql = "SELECT password FROM shop WHERE shopname = '$shopname' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    if (password_verify($password, $row[0])) {
       $sql = "UPDATE shop SET password = '$newpassword_Hash', updated_at = NOW() WHERE shopname = '$shopname' ";
       $result = $connectNow->query($sql);
       echo "Update Success";
    }
    else {
       echo "Wrong Password";
    }
} else echo "no data\n";

?>
