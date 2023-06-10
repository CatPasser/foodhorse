<?php require("to_mysql_use.php"); ?>

<button onclick="document.location.href='./'">登出</button><br>
<hr>

<?php
    require("conn_mysql.php");
    // echo"<hr>";
    $list=$_POST['list'];
    $String="";

    $a3[]=array(1,2,3,4,5,6,7,8,9);
    $a1=mysqli_query($db_link,"DESCRIBE $list");
    // echo "<table border=1 width=300px height=280px>";
    // echo "<tr>";
    for($i=0;$i<mysqli_num_fields($a1);$i++) {
        $field_info = mysqli_fetch_field($a1);
        // echo "<td>$field_info->name</td>";
    }
    // echo "</tr>";
    $a2=0;
    while($row=mysqli_fetch_row($a1)){
        $a2++;
        
        $a3[$a2]=$row;
        // echo "<tr>";
        
        for ($i=0;$i<mysqli_num_fields($a1);$i++){
        
        // echo "<td>$row[$i]</td>";
        
        }
    
    // echo "</tr>";
    
    }
    // echo "</table>";
    // echo"<hr>";
    $input_1=$_POST["0"];
    // echo "$input_1";
    $a2=0;
    if($list=="請選擇資料表"){require("to_mysql_use.php");}
    else{
        $all_1=mysqli_query($db_link,"SELECT * FROM $list");
        
        for ($i=0;$i<mysqli_num_fields($all_1);$i++){
            $input[$i]=$_POST["$i"];
            $field_info = mysqli_fetch_field($all_1);
            if($input[$i]=="" && $a3[$i+1][2]=="YES" && strstr($a3[$i+1][1],"timestamp")){
                $input[$i]="null";
                
            }
            
            //預設null
            elseif($input[$i]=="" && $a3[$i+1][2]=="YES" && strstr($a3[$i+1][1],"int")){
                $input[$i]="";
                continue;
            }
            elseif($input[$i]=="" && $a3[$i+1][2]=="YES"){
                $input[$i]=" ";
                continue;
            }
            elseif($input[$i]==""){
                $a2++;
                $String=$String.$field_info->name."不能為空<br>";
            }
            else{
                
            }
            // echo"<hr>$input[$i]<hr>";
            if($i==0){continue;}
            
            if((strstr($a3[$i+1][1],"timestamp"))){

            }
            elseif((strstr($a3[$i+1][1],"int"))&& ($input[$i]=="")){
                continue;
            }
            elseif((strstr($a3[$i+1][1],"int"))&& (!is_numeric($input[$i]))){
                echo " ".$a3[$i+1][0]."不能為字串<br>";
                $input[$i]="";
                $a2++;
            }
            
            
            elseif((strstr($a3[$i+1][1],"int"))){
                // echo"".$input[$i]+222;
                // $input[$i]=(int)($input[$i]);
                // echo"".$input[$i]+222;
            }
            elseif((strstr($a3[$i+1][1],"varchar"))&& (is_string($input[$i]))){

            }
            elseif((strstr($a3[$i+1][1],"text"))&& (is_string($input[$i]))){

            }
            
            else{
                
            }
            
        }
        
        echo"$String <hr>";
        $sql_query="SELECT * FROM $list";
        $result=mysqli_query($db_link,$sql_query);
        if($a2!=0){
            echo"<form method='POST' action='mysql_new.php'>";
            
            $sql_query="SELECT * FROM $list";
            $result=mysqli_query($db_link,$sql_query);
        
            echo"<select name='list'><option>$list</option></select><br>";
            echo "<table border=1 width=400 cellpadding=5>";
            echo"<tr>";
            for($i=0;$i<mysqli_num_fields($result);$i++) {
                $field_info = mysqli_fetch_field($result);
                echo "<td>$field_info->name</td>";
            }
            echo"</tr>";
            echo "<tr>";

            
            echo "<td><select name='0'><option>$input_1</option> </td>";
            
            for($i=1;$i<mysqli_num_fields($result);$i++) {
                if($a3[$i+1][1]=="timestamp"){
                    echo '<td><input type="datetime-local" name="'.$i.'"value="'.$input[$i].'"></input></td>';
                }
                else{
                    echo '<td><input type="text" name="'.$i.'"value="'.$input[$i].'"></input></td>';   
                }
            }
            echo"</tr>";
            echo "</table><button >新增</button>";
            echo "<button type=button onclick="."document.location.href='wait.php'".">取消</button></from><br>";
            echo"<hr>";
        }
        else{   
                $String_1="";
                $String_2="";
                
                for($i=0;$i<mysqli_num_fields($result);$i++){
                    // echo"<hr>$input[$i]<hr>";
                    $field_info = mysqli_fetch_field($result);
                    if($a3[$i+1][1]!="int" && !($a3[$i+1][1]=="timestamp" && $input[$i]=="null")){
                        $input[$i]="'".$input[$i]."'";
                    }
                    if(mysqli_num_fields($result)-$i==1){
                        $String_1=$String_1.$a3[$i+1][0];
                        $String_2=$String_2.$input[$i];
                    }else{
                        $String_1=$String_1.$a3[$i+1][0].",";
                        $String_2=$String_2.$input[$i].",";
                    }
                    

                }
                // echo"$String_1<br>";
                // echo"$String_2<hr>";

                $sql_query_1="INSERT INTO $list VALUES (".$String_2.")";
                
                $result=mysqli_query($db_link,$sql_query_1);
                
            }
        
        
        
          
        
        
        $sql_query="SELECT * FROM $list";
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
        
        // echo"<hr>";
    }
?>
