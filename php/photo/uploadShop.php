<?php
$filename = date("d-m-Y").'-'.time().'-'.'.webp';
$path = '/var/www/html/photo/shopPhoto/upload/'.$filename;
if(isset($_POST["image"])){
   if(file_put_contents($path,base64_decode($_POST['image']))){
      echo "Success";
   } else echo "Failed to upload image";
}
?>

