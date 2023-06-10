package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class User_History_Order extends AppCompatActivity {

    private ListView listView;
    MainApp mainApp;
    List<String> oID ;
    List<String> shopname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history_order);
        mainApp = (MainApp) getApplication();
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(User_History_Order.this,User_Order_Info.class);
                intent.putExtra("oID",oID.get(i));
                intent.putExtra("shopname",shopname.get(i));
                startActivity(intent);
            }
        });
        String[] field = new String[1];
        field[0] = "uID";
        String[] data = new String[1];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        String result = use.getResult("http://20.187.122.219/users/orders/history.php", field, data);
        if (!result.equals("No Order")){
            String array[] = result.split("&&");//oID 店家名稱 狀態
            oID = new ArrayList<>();
            shopname = new ArrayList<>();
            for (int i2 = 0; i2 < array.length; i2++) {
                String array2[] = array[i2].split(",");
                oID.add(array2[0]);
                if (array2[2].equals("已完成"))
                {
                    shopname.add("未評價-"+array2[1]);
                }
                else
                {
                    shopname.add(array2[1]);
                }
            }
            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,shopname));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] field = new String[1];
        field[0] = "uID";
        String[] data = new String[1];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        String result = use.getResult("http://20.187.122.219/users/orders/history.php", field, data);
        if (!result.equals("No Order")){
            String array[] = result.split("&&");//oID 店家名稱 狀態
            oID = new ArrayList<>();
            shopname = new ArrayList<>();
            for (int i2 = 0; i2 < array.length; i2++) {
                String array2[] = array[i2].split(",");
                oID.add(array2[0]);
                if (array2[2].equals("已完成"))
                {
                    shopname.add("未評價-"+array2[1]);
                }
                else
                {
                    shopname.add(array2[1]);
                }
            }
            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,shopname));
        }
    }
}