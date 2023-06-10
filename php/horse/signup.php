<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$horsename = $_POST['account'];
$password = $_POST['password'];
$phone = $_POST['phone'];
$password_Hash = password_hash($password, PASSWORD_DEFAULT);


if($horsename != null && $password != null && $phone != null){
    $data = true;
    $sql="SELECT * FROM horse WHERE horsename = '$horsename' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_assoc($result);
    if (mysqli_num_rows($result) != 0) {
        $signup = false;
    }
    else {
        $sql="INSERT INTO horse SET horsename = '$horsename', password = '$password_Hash', phone = '$phone', created_at = NOW() ";
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
