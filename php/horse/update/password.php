<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$horsename = $_POST['horsename'];
$password = $_POST['password'];
$newpassword = $_POST['newpassword'];
$newpassword_Hash = password_hash($newpassword, PASSWORD_DEFAULT);

if( $password != null && $newpassword != null){
    $sql = "SELECT password FROM horse WHERE horsename = '$horsename' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    if (password_verify($password, $row[0])) {
       $sql = "UPDATE horse SET password = '$newpassword_Hash', updated_at = NOW() WHERE horsename = '$horsename' ";
       $result = $connectNow->query($sql);
       echo "Update Success";
    }
    else {
       echo "Wrong Password";
    }
} else echo "no data\n";

?>
