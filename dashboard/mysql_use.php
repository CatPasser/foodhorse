<?php
    header('Content-Type: text/html; charset=utf-8');
    $list=$_POST['list'];
    $dowhat=$_POST['dowhat'];
    $a1=true;
    
    require("conn_mysql.php");

    if($list=="請選擇資料表"){
		$list=" ";
	}
    function get_1 ($array_1,$result,$a1){
        $j=count($array_1);
		echo "<tr>";
        if($a1=="delete"){
            echo "<td>勾選</td>";
        }
        for($i=0;$i<$j;$i++){
            echo "<td>$array_1[$i]</td>";
        }
            
        echo"</tr>";
        while($row=mysqli_fetch_array($result)){
        echo "<tr>";
        if($a1=="delete"){
            echo "<td><input type=checkbox /></td>";
        }
        for($i=0;$i<$j;$i++){
            echo "<td>$row[$i]</td>";
        }
        echo"</tr>";
        }
    }
    require("to_mysql_use.php");
    if($dowhat=="全部"){
        
        if($list!=" "){
            $sql_query="SELECT * FROM $list";
            $result=mysqli_query($db_link,$sql_query);
            echo "<table border=1 width=400 cellpadding=5>";
            

            echo "<tr>";

            for($i=0;$i<mysqli_num_fields($result);$i++) {
                $field_info = mysqli_fetch_field($result);
                echo "<td>$field_info->name</td>";
            }
            echo "</tr>";
            while($row=mysqli_fetch_row($result)){
                echo "<tr>";
                for ($i=0;$i<mysqli_num_fields($result);$i++){
                echo "<td>$row[$i]</td>";

                }

            echo "</tr>";

            }
            
            echo "</table>";
        }
        else{
        echo"登入失敗";
        }
    }
    elseif($dowhat=="查詢"){
        echo"<form method='POST' action='mysql_choose.php'>";
        if($list!=" "){
            $sql_query="SELECT * FROM $list";
            $result=mysqli_query($db_link,$sql_query);
        
            echo"<select name='list'><option>$list</option></select><br>";
            echo "<table border=1 width=400 cellpadding=5>";
            
            for($i=0;$i<mysqli_num_fields($result);$i++) {
                $field_info = mysqli_fetch_field($result);
                echo "<td>$field_info->name</td>";
            }
            echo"</tr>";
            echo "<tr>";
            for($i=0;$i<mysqli_num_fields($result);$i++) {
                
                echo '<td><input type="text" name="'.$i.'"></input></td>';
            }
            echo"</tr>";
            echo "</table><button>查詢</button></from><br>";
        }
        else{require("to_mysql_use.php");}
    }

    elseif($dowhat=="新增"){
        $a1=mysqli_query($db_link,"DESCRIBE $list");
        
        $a2=0;
        while($row=mysqli_fetch_row($a1)){
            $a2++;
            
            $a3[$a2]=$row;
            
        }

        echo"<form method='POST' action='mysql_new.php'>";
        if($list!=" "){
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
            $a4=0;
            while($row=mysqli_fetch_row($result)){
                
                    
                $a4=max($a4,$row[0])+1;
            }
            echo "<td><select name='0'><option>$a4</option> </td>";
            
            for($i=1;$i<mysqli_num_fields($result);$i++) {
                if($a3[$i+1][1]=="timestamp"){
                    echo '<td><input type="datetime-local" name="'.$i.'"value=" "></input></td>';
                }
                else{
                    echo '<td><input type="text" name="'.$i.'"></input></td>';
                }
                
            }
            echo"</tr>";
            echo "</table><button >新增</button>";
            echo "<button type=button onclick="."document.location.href='wait.php'".">取消</button></from><br>";
            
        }
        else{require("to_mysql_use.php");}
    }
    if($dowhat=="刪除"){
        if($list!=""){
            $sql_query="SELECT * FROM $list";
            $result=mysqli_query($db_link,$sql_query);
            echo"<form method='POST' action='mysql_del.php'>";
            echo"<select name='list'><option>$list</option></select><br>";
            echo "<table border=1 width=400 cellpadding=5>";
            

            echo "<tr>";

            for($i=0;$i<mysqli_num_fields($result);$i++) {
                $field_info = mysqli_fetch_field($result);
                echo "<td>$field_info->name</td>";
            }
            echo "<td>勾選刪除</td>";
            echo "</tr>";
            $i1=0;
            while($row=mysqli_fetch_row($result)){
                echo "<tr>";
                for ($i=0;$i<mysqli_num_fields($result);$i++){
                echo "<td>$row[$i]</td>";

                }
                echo '<td><input type="hidden" name='.$i1.' value="off"></input>';
                echo' <input type="checkbox" name='.$i1.' value="on"></input></td>';
                $i1++;

            }
            echo "</tr>";
            
            echo "</table><button >刪除</button>";
            echo "<button type=button onclick="."document.location.href='wait.php'".">取消</button></from><br>";
        }
        else{
            require("to_mysql_use.php");
        }
    }
    // elseif($dowhat=="更新"){
    //     echo"<form method='POST' action='mysql_choose.php'>";
    //     if($list!=" "){
    //         $sql_query="SELECT * FROM $list";
    //         $result=mysqli_query($db_link,$sql_query);
    //     }
    //     echo"<select name='list'><option>$list</option></select><br>";
    //     echo "<table border=1 width=400 cellpadding=5>";

    //     for($i=0;$i<mysqli_num_fields($result);$i++) {
    //         $field_info = mysqli_fetch_field($result);
    //         echo "<td>$field_info->name</td>";
    //     }
    //     echo"</tr>";
    //     echo "<tr>";
    //     for($i=0;$i<mysqli_num_fields($result);$i++) {
            
    //         echo '<td><input type="text" name="'.$i.'"></input></td>';
    //     }

    //     echo "</table><button>更新</button></from><br>";
    // }
    // else{
    //     require("to_mysql_use.php");
    // }  
    
?>
