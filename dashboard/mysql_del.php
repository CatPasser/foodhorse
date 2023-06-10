<?php require("to_mysql_use.php"); ?>

<button onclick="document.location.href='./'">登出</button><br>
<hr>

<?php
    require("conn_mysql.php"); 
    $list=$_POST['list'];
    // echo"$list<hr>";
    
    $a3[]=array(1,2,3,4,5,6,7,8,9,10);
    $a1=mysqli_query($db_link,"SELECT * FROM $list");
    
   echo "<table border=1 width=300px height=280px>";
    echo "<tr>";
    for($i=0;$i<mysqli_num_fields($a1);$i++) {
        $field_info = mysqli_fetch_field($a1);
        echo "<td>$field_info->name</td>";
        if($i==0){
            $a3_1=$field_info->name."";
            // echo"$a3_1 ";
        }
        
    }
    echo "</tr>";
    
    $a2=0;
    while($row=mysqli_fetch_row($a1)){
        $a2++;
        
        $a3[$a2]=$row[0];
        echo "<tr>";
        
        for ($i=0;$i<mysqli_num_fields($a1);$i++){
        
        echo "<td>$row[$i]</td>";
        
        }
    
    echo "</tr>";
    
    }
    echo "</table>";
    
    
    

    $all_1=mysqli_query($db_link,"SELECT * FROM $list");
    $String_1="";
    $a2=0;
    $i=0;
    while($row=mysqli_fetch_row($all_1)){
        
        $input[$i]=$_POST["$i"];
        
        // if($input[$i]=="" && $a3[$i+1][2]=="YES" && !strstr($a3[$i+1][1],"int")){
        //     $input[$i]="null";
            
        // }
        
        if($input[$i]=="on"){
            if($a2!=0){
                $String_1=$String_1.",";
            }
            echo"$input[$i]<hr>";
            $a2++;
            // echo"$a2<hr>";
            
            $String_1=$String_1.$a3[$i+1];
            
            
    
        }
        else{
            echo"$input[$i]<hr>";
        }
        $i++;
    }
    echo"$a3_1  $String_1<hr>";

    if($a2!=0){
        if(mysqli_num_fields($all_1)==1){
            $sql_query_1="DELETE FROM ".$list." WHERE ".$a3_1."=".$String_1."";
        }
        else{
            $sql_query_1="DELETE FROM ".$list." WHERE ".$a3_1." IN (".$String_1.")";
        }
        
        $all_1=mysqli_query($db_link,$sql_query_1);
    }

    echo"<hr>";
    $result=mysqli_query($db_link,"SELECT * FROM $list");
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

    

