package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class CommodityInfo extends AppCompatActivity {

    private TextView name;
    private TextView tag;
    private TextView price;
    private TextView notes;
    private ImageView photo;
    private MainApp mainApp;
    private Entuty_Commodity entuty_commodity;
    private Entuty_User entuty_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_info);
        mainApp = (MainApp) getApplication();
        entuty_commodity = mainApp.getEntuty_commodity();
        entuty_user = mainApp.getEntuty_user();
        photo = (ImageView) findViewById(R.id.photo);
        name = (TextView) findViewById(R.id.name);
        tag = (TextView) findViewById(R.id.tag);
        price = (TextView) findViewById(R.id.price);
        notes = (TextView) findViewById(R.id.notes);
        new use.ImageLoad(photo,entuty_commodity.getCommodity_photo()).execute();

        String label[] = {"餐點名稱","類別","價格","備註"};
        String value[] = {entuty_commodity.getCommodity_name(),entuty_commodity.getCommodity_tag(),entuty_commodity.getCommodity_price(),entuty_commodity.getCommodity_notes()};
        name.setText(label[0]+":"+value[0]);
        tag.setText(label[1]+":"+value[1]);
        price.setText(value[2]);
        notes.setText(label[3]+":"+value[3]);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityInfo.this,Update_Commodity_name.class);
                startActivity(intent);
            }
        });

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityInfo.this,Update_Commodity_tag.class);
                startActivity(intent);
            }
        });

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityInfo.this,Update_Commodity_price.class);
                startActivity(intent);
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityInfo.this,update_commodity_notes.class);
                startActivity(intent);
            }
        });

    }
    public void takeOf(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(CommodityInfo.this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(CommodityInfo.this);
        builder.setTitle("即將下架餐點");
        builder.setMessage("確定要下架餐點嗎");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String[] field = new String[3];
                field[0] = "sID";
                field[1] = "foodname";
                field[2] = "tag";
                String[] data = new String[3];
                data[0] = Integer.toString(entuty_user.getID());
                data[1] = entuty_commodity.getCommodity_name();
                data[2] = entuty_commodity.getCommodity_tag();
                String result = use.getResult("http://20.187.122.219/shop/food/takeOf.php", field, data);
                builder2.setTitle("Nice");
                builder2.setMessage("下架成功");
                builder2.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder2.create();
                dialog.show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        String label[] = {"餐點名稱","類別","價格","備註"};
        String value[] = {entuty_commodity.getCommodity_name(),entuty_commodity.getCommodity_tag(),entuty_commodity.getCommodity_price(),entuty_commodity.getCommodity_notes()};
        name.setText(label[0]+":"+value[0]);
        tag.setText(label[1]+":"+value[1]);
        price.setText(value[2]);
        notes.setText(label[3]+":"+value[3]);
    }
}