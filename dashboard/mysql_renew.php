<?php


require("conn_mysql.php");
$res=mysqli_query($db_link,"select * from books");

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

echo "</table>";

mysqli_free_result($res);

?>