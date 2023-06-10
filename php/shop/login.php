<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$shopname = $_POST['account'];
$password = $_POST['password'];
$password_Hash = password_hash($password, PASSWORD_DEFAULT);

if($shopname != null && $password != null){
    $sql="SELECT * FROM shop WHERE shopname = '$shopname' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    if (mysqli_num_rows($result) != 0) {
        $dbshopname = $row[1];
        $dbpassword = $row[2];
        if ($dbshopname == $shopname && password_verify($password, $dbpassword)) {
            $login = true;
        } else $login = false;
    }
    else {
        $login = false;
    }
} else echo "no data\n";


if ($login == true) {
   if($row[5] == null){
      $row[5] = "null";
   } elseif($row[6] == null) {
      $row[6] = "null";
   }
   echo "Login Success",",",$row[0],",",$row[1],",",$row[3],",",$row[4],",";
   echo $row[5],",",$row[6],",",json_encode($row[7]),",",$row[8];
} else echo "Login Failed\n";

?>
