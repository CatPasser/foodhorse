<?php
require '/var/www/html/connect.php';
require '/var/www/html/photo/uploadFood.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$sID = $_POST['sID'];
$foodname = $_POST['foodname'];
$tag = $_POST['tag'];
$price = $_POST['price'];
$photo = "photo/foodPhoto/upload/".$filename;
$notes = $_POST['notes'];

if($notes == null){
   $notes == "";
}
if($foodname != null && $tag != null && $price != null) {
    $data = true;
    $sql="SELECT foodname FROM food WHERE sID = '$sID' AND foodname = '$foodname' ";
    $result = $connectNow->query($sql);
    $row = mysqli_fetch_row($result);
    if (mysqli_num_rows($result) != 0) {
       $putOn = false;
    }
    else {
       $putOn = true;
       $sql="INSERT INTO food SET sID = '$sID', foodname = '$foodname', tag = '$tag',  photo = '$photo', price = '$price', notes = '$notes', created_at = NOW() ";
       $result = $connectNow->query($sql);
    }
} else $data = false;

if ($data == false) {
    echo "no data";
} else if ($putOn == true) {
    echo "Put On Success";
} else {
    echo "Duplicate Food";
}

?>
