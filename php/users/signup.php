<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$username = $_POST['account'];
$password = $_POST['password'];
$phone = $_POST['phone'];
$password_Hash = password_hash($password, PASSWORD_DEFAULT);

if($username != null && $password != null && $phone != null){
    $data = true;
    $sql="SELECT * FROM users WHERE username = '$username'";
    $result = $connectNow->query($sql);
    $row = mysqli_num_rows($result);
    if (mysqli_num_rows($result) != 0) {
        $signup = false;
    }
    else {
        $sql="INSERT INTO users SET username='$username', password='$password_Hash', phone='$phone', created_at=NOW()";
        $result = $connectNow->query($sql);
        $signup = true;
    }
} else $data = false;

if ($data == false) {
    echo "no data";
} else if ($signup == true) {
    echo "Sign up Success";
} else echo "duplicate account";

?>
