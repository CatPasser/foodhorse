<?php
 $db_link = new mysqli("127.0.0.1","master","HsaOPpRkj3c6Hqqp", "foodhorse");
 if(!$db_link){
//   die("資料庫連線失敗<br>");
 }else{
  echo"資料庫連線成功<br>";
 }
 mysqli_query($db_link,"SET NAMES 'utf8'");  //設定字元集與編碼為utf-8
 ?>