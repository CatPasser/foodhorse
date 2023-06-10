package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class User_Order_Info extends AppCompatActivity {

    ScrollView view;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_info);

        view = (ScrollView) findViewById(R.id.view);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        TextView shopname = new TextView(this);
        shopname.setTextColor(Color.BLACK);
        shopname.setTextSize(30);
        shopname.setGravity(Gravity.CENTER);;

        TextView shopphone = new TextView(this);
        shopphone.setTextColor(Color.BLACK);
        shopphone.setTextSize(20);

        TextView shopaddress = new TextView(this);
        shopaddress.setTextColor(Color.BLACK);
        shopaddress.setTextSize(20);

        TextView username = new TextView(this);
        username.setTextColor(Color.BLACK);
        username.setTextSize(20);

        TextView userphone = new TextView(this);
        userphone.setTextColor(Color.BLACK);
        userphone.setTextSize(20);

        TextView useraddress = new TextView(this);
        useraddress.setTextColor(Color.BLACK);
        useraddress.setTextSize(20);

        TextView ordernotes = new TextView(this);
        ordernotes.setTextColor(Color.BLACK);
        ordernotes.setTextSize(20);

        TextView orderprice = new TextView(this);
        orderprice.setTextColor(Color.BLACK);
        orderprice.setTextSize(20);
        orderprice.setGravity(Gravity.RIGHT);

        TextView orderinfo = new TextView(this);
        orderinfo.setTextColor(Color.BLACK);
        orderinfo.setTextSize(20);
        orderinfo.setBackgroundColor(Color.rgb(204,209,202));

        TextView orderstate = new TextView(this);
        orderstate.setTextColor(Color.BLACK);
        orderstate.setTextSize(26);
        orderstate.setGravity(Gravity.CENTER);
        orderstate.setHeight(150);

        Intent intent = getIntent();

        Button star = new Button(this);
        star.setText("評價餐點");
        star.setGravity(Gravity.CENTER);
        star.setVisibility(View.GONE);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alearDialog = new AlertDialog.Builder(User_Order_Info.this);
                View vvv = getLayoutInflater().inflate(R.layout.evaluate,null);
                alearDialog.setTitle("評價餐點");
                alearDialog.setView(vvv);
                alearDialog.setPositiveButton("送出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        RatingBar ratingBar = vvv.findViewById(R.id.rating);
                        int num = (int) ratingBar.getRating();
                        String[] field2 = new String[2];
                        field2[0] = "star";
                        field2[1] = "oID";
                        String[] data2 = new String[2];
                        data2[0] = Integer.toString(num);
                        data2[1] = intent.getStringExtra("oID");
                        String result2 = use.getResult("http://20.187.122.219/users/evaluation/shop.php", field2, data2);
                        star.setVisibility(View.GONE);
                    }
                });
                AlertDialog dialog = alearDialog.create();
                dialog.show();
            }
        });

        Button EndOrder = new Button(this);
        EndOrder.setText("完成訂單");
        EndOrder.setGravity(Gravity.CENTER);
        EndOrder.setVisibility(View.GONE);
        EndOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alearDialog = new AlertDialog.Builder(User_Order_Info.this);
                View vvv = getLayoutInflater().inflate(R.layout.evaluate,null);
                alearDialog.setTitle("請給外送員評價以完成訂單");
                alearDialog.setView(vvv);
                alearDialog.setPositiveButton("送出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        RatingBar ratingBar = vvv.findViewById(R.id.rating);
                        int num = (int) ratingBar.getRating();
                        String[] field2 = new String[2];
                        field2[0] = "star";
                        field2[1] = "oID";
                        String[] data2 = new String[2];
                        data2[0] = Integer.toString(num);
                        data2[1] = intent.getStringExtra("oID");
                        String result2 = use.getResult("http://20.187.122.219/users/evaluation/horse.php", field2, data2);

                        String[] field = new String[1];
                        field[0] = "oID";
                        String[] data = new String[1];
                        data[0] = intent.getStringExtra("oID");
                        String result = use.getResult("http://20.187.122.219/users/orders/finishCheck.php", field, data);

                        finish();
                    }
                });
                AlertDialog dialog = alearDialog.create();
                dialog.show();
            }
        });


        String[] field = new String[1];
        field[0] = "oID";
        String[] data = new String[1];
        data[0] = intent.getStringExtra("oID");
        //店家電話 店家住址 訂單送達住址 訂單備註 總金額 外送員名稱 外送員電話 訂單狀態
        String result = use.getResult("http://20.187.122.219/users/orders/information.php", field, data);
        if(!result.equals("Error"))
        {
            String array[] = result.split("&&");//資訊 訂單
            String array2[] = array[0].split(",");
            String array3[] = array[1].split(",");
            username.setText("外送員名稱:"+array2[5]);
            userphone.setText("外送員電話:"+array2[6]);
            shopname.setText(intent.getStringExtra("shopname"));
            shopphone.setText("店家電話:"+array2[0]);
            shopaddress.setText("店家地址:"+array2[1]);
            useraddress.setText("送達地址:"+array2[2]);
            ordernotes.setText("備註:"+array2[3]);
            orderprice.setText("總金額:"+array2[4]);
            orderstate.setText(array2[7]);
            if (array2[7].equals("已送達"))
            {
                EndOrder.setVisibility(View.VISIBLE);
                orderstate.setBackgroundColor(Color.rgb(117,225,22));

            }
            else if (array2[7].equals("外送員配對中")||array2[7].equals("餐點配送中"))
            {
                orderstate.setBackgroundColor(Color.rgb(226,114,16));
            }
            if (array2[7].equals("已完成"))
            {
                star.setVisibility(View.VISIBLE);

            }

            String info = "";
            for(int i = 0 ; i < array3.length;i++)
            {
                info+=array3[i]+"\n";
            }
            orderinfo.setText(info);
        }
        linearLayout.addView(orderstate);
        linearLayout.addView(shopname);
        linearLayout.addView(shopphone);
        linearLayout.addView(shopaddress);
        linearLayout.addView(username);
        linearLayout.addView(userphone);
        linearLayout.addView(useraddress);
        linearLayout.addView(orderinfo);
        linearLayout.addView(orderprice);
        linearLayout.addView(ordernotes);
        linearLayout.addView(EndOrder);
        linearLayout.addView(star);
    }
}