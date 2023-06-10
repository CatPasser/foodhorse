<?php
	require("conn_mysql.php");
    echo "
    <form method='POST' action='mysql_use.php'>
	<select name='list'>
		<option>請選擇資料表</option>";
    
    $res=mysqli_query($db_link,"SELECT table_name FROM information_schema.TABLES WHERE table_schema = 'foodhorse'");
    //所有在 FOODHORSE 裡的 TABLE
    while($row=mysqli_fetch_row($res)){

        for ($i=0;$i<mysqli_num_fields($res);$i++){
        echo "<option>$row[$i]</option>";
        }
    }
	echo"</select>
	<select name='dowhat'>
		<option>請選擇功能</option>
		<option>全部</option>
		<option>查詢</option>
		<option>更新</option>
		<option>刪除</option>
		<option>新增</option>
	</select>
	<button >確認</button>
</form>";
