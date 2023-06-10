<?php

// $db_link=@mysqli_connect("127.0.0.1","root","");
// 	if(!$db_link){
// 		die("資料庫連線失敗<br>");
// 	}else{
// 		echo"資料庫連線成功<br>";
// 	}
// mysqli_query($db_link,"SET NAMES 'utf8'");  //設定字元集與編碼為utf-8
require("conn_mysql.php");
$res=mysqli_query($db_link,"SELECT * FROM Information_Schema.TABLES WHERE table_schema = 'foodhorse'");
    echo "<table border=1 width=300px height=280px>";

    echo"<tr>";
    for($i=0;$i<mysqli_num_fields($res);$i++) {
        $field_info = mysqli_fetch_field($res);
        echo "<td>$field_info->name</td>";
    }
    echo"</tr>";
    while($row=mysqli_fetch_row($res)){

    echo "<tr>";
    
    for ($i=0;$i<mysqli_num_fields($res);$i++){
    
    echo "<td>$row[$i]</td>";
    
    }
    
    echo "</tr>";
    
    }
    echo "</table><br><hr><br>";

    /**/
$res=mysqli_query($db_link,"DESCRIBE shop");
    echo "<table border=1 width=300px height=280px>";
    echo "<tr>";
    for($i=0;$i<mysqli_num_fields($res);$i++) {
        $field_info = mysqli_fetch_field($res);
        echo "<td>$field_info->name</td>";
    }
    echo "</tr>";
    while($row=mysqli_fetch_row($res)){

    echo "<tr>";
    
    for ($i=0;$i<mysqli_num_fields($res);$i++){
    
    echo "<td>$row[$i]</td>";
    
    }
    
    echo "</tr>";
    
    }
    
    