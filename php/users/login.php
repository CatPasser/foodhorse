<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$username = $_POST['account'];
$password = $_POST['password'];

if($username != null && $password != null){
    $sql="SELECT * FROM users WHERE username = '$username'";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    if (mysqli_num_rows($result) != 0) {
        $dbusername = $row[1];
        $dbpassword = $row[2];
        if ($dbusername == $username &&  password_verify($password, $dbpassword)) {
            $login = true;
        } else $login = false;
    }
    else {
        $login = false;
    }
} else echo "no data\n";


if ($login == true) {
   if($row[5] == null){
   $row[5] = " ";
   }
   echo "Login Success",",",$row[0],",",$row[1],",",json_encode($row[3]),",",$row[4],",",$row[5];
} else echo "Login Failed\n";

?>
