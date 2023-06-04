package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Driver_Shop_Order extends AppCompatActivity {

    private ImageView imageView;
    private ListView listView;
    private Driver_Shop_Order_listadapter listadapter;
    private TextView shopname;
    private MainApp mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_shop_order);
        mainApp = (MainApp) getApplication();
        imageView = (ImageView) findViewById(R.id.image);
        listView = (ListView) findViewById(R.id.list_view);
        shopname = (TextView) findViewById(R.id.shopname);
        Intent intent = getIntent();
        shopname.setText(intent.getStringExtra("name"));
        String[] field = new String[1];
        field[0] = "address";
        String[] data = new String[1];
        data[0] = intent.getStringExtra("address");
        String result = use.getResult("http://20.187.122.219/shop/orders/getOrder.php", field, data);
        if (!result.equals("No Order")){
            String array[] = result.split("&&");//oID address
            List<String> oID = new ArrayList<>();
            List<String> address = new ArrayList<>();
            for (int i2 = 0; i2 < array.length; i2++) {
                String array2[] = array[i2].split(",");
                oID.add(array2[0]);
                address.add(array2[1]);
            }

            listadapter = new Driver_Shop_Order_listadapter(getApplicationContext(),address,oID,Integer.toString(mainApp.getEntuty_user().getID()),this,Driver_Shop_Order.this);
            listView.setAdapter(listadapter);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String[] field = new String[1];
        field[0] = "address";
        String[] data = new String[1];
        data[0] = intent.getStringExtra("address");
        String result = use.getResult("http://20.187.122.219/shop/orders/getOrder.php", field, data);
        if (!result.equals("No Order")) {
            String array[] = result.split("&&");//oID address
            List<String> oID = new ArrayList<>();
            List<String> address = new ArrayList<>();
            for (int i2 = 0; i2 < array.length; i2++) {
                String array2[] = array[i2].split(",");
                oID.add(array2[0]);
                address.add(array2[1]);
            }

            listadapter = new Driver_Shop_Order_listadapter(getApplicationContext(), address, oID, Integer.toString(mainApp.getEntuty_user().getID()), this, Driver_Shop_Order.this);
            listView.setAdapter(listadapter);
        }
    }

    public void close(String oID,String hID)
    {
        //Intent intent = new Intent(this,Driver_Shop_Order_Information.class);
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();

    }
}