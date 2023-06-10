<?php require("to_mysql_use.php"); ?>

<button onclick="document.location.href='./'">登出</button><br>

<?php
    
    
    $String=" WHERE ";
    

    $list=$_POST['list'];
    if($list=="請選擇資料表"){}
    else{
        $all_1=mysqli_query($db_link,"SELECT * FROM $list");

        

        for ($i=0;$i<mysqli_num_fields($all_1);$i++){
            $input[$i]=$_POST["$i"];
            
        }
        echo"<br>";
        
        require("conn_mysql.php");
        
        for($i=0;$i<mysqli_num_fields($all_1);$i++){
            $field_info = mysqli_fetch_field($all_1);
            if($input[$i]!=""){
                
                if($String!=" WHERE "){
                    $String=$String.' OR ';
                }
                if(is_numeric('$input[$i]')){
                    $String=$String."`".$field_info->name."`= ".$input[$i]." ";
                }
                else{
                    
                    $String=$String."`".$field_info->name."`= '".$input[$i]."' ";
                }
            }   
        }   
        
        if($String==" WHERE "){$String="";}
        $sql_query="SELECT * FROM $list $String";
        // $sql_query="SELECT * FROM $list "
        $result=mysqli_query($db_link,$sql_query);
        echo "<table border=1 width=400 cellpadding=5>";

        echo "<tr>";
        for($i=0;$i<mysqli_num_fields($result);$i++) {
            $field_info = mysqli_fetch_field($result);
            echo "<td>$field_info->name</td>";
        }
        echo"</tr>";
        
        while($row=mysqli_fetch_row($result)){
            echo "<tr>";
            for ($i=0;$i<mysqli_num_fields($result);$i++){
            echo "<td>$row[$i]</td>";
            } 
            echo "</tr>";
        }
        
        echo "</table>";

    }
?>
