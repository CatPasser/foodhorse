<?php
require '/var/www/html/connect.php';
mysqli_query($connectNow,"SET NAMES 'UTF8'");

$text = $_POST['text'];

if($text == null){
   echo "No data";
}
else{
   $sql = "SELECT sID,fullname,tag,photo,address FROM shop WHERE (fullname LIKE '%$text%' OR tag LIKE '%$text%') AND photo IS NOT NULL AND address IS NOT NULL ";
   $result = $connectNow->query($sql);
   if (mysqli_num_rows($result) == 0) {
      echo "No query result";
   }
   else{
      while ($row = $result->fetch_assoc()) {
         if($row['tag'] == null){
            $row['tag'] = 'null';
         } else if($row['photo'] == null){
            $row['photo'] = 'null';
         }
         echo $row['sID'],",";
         echo $row['fullname'],",",$row['tag'],",",$row['photo'];
	 echo "&&";
      }
   }
}

?>
