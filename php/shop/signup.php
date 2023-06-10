<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$shopname = $_POST['account'];
$password = $_POST['password'];
$phone = $_POST['phone'];
$fullname = $_POST['fullname'];

$password_Hash = password_hash($password, PASSWORD_DEFAULT);


if($shopname != null && $password != null && $phone != null && $fullname != null){
    $data = true;
    $sql="SELECT * FROM shop WHERE shopname = '$shopname' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_assoc($result);
    if (mysqli_num_rows($result) != 0) {
        $signup = false;
    }
    else {
        $sql="INSERT INTO shop SET shopname = '$shopname', password = '$password_Hash', phone = '$phone', fullname = '$fullname', created_at = NOW() ";
        $result = $connectNow->query($sql);
        $signup = true;
    }
} else $data = false;


if ($data == false) {
    echo "no data";
} else if ($signup == true) {
    echo "Sign up Success\n";
} else echo "duplicate account"; 

?>
