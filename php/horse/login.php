<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$horsename = $_POST['account'];
$password = $_POST['password'];
$password_Hash = password_hash($password, PASSWORD_DEFAULT);


if($horsename != null && $password != null){
    $sql="SELECT * FROM horse WHERE horsename = '$horsename' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    if (mysqli_num_rows($result) != 0) {
        $dbhorsename = $row[1];
        $dbpassword = $row[2];
        if ($dbhorsename == $horsename && password_verify($password, $dbpassword)) {
            $login = true;
        } else $login = false;
    }
    else {
        $login = false;
    }
} else echo "no data\n";


if ($login == true) {
   echo "Login Success",",",$row[0],",",$row[1],",",json_encode($row[3]),",",$row[4],",",$row[5];
} else echo "Login Failed\n";

?>
