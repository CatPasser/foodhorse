<?php
require '/var/www/html/connect.php';
require '/var/www/html/photo/uploadShop.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$shopname = $_POST['shopname'];
$photo = "photo/shopPhoto/upload/".$filename;

if($shopname != null){
   $sql = "UPDATE shop SET photo = '$photo', updated_at = NOW() WHERE shopname = '$shopname' ";
   $result = $connectNow->query($sql);
   echo $photo;
}
?>
