package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Browse_Shop_Commodity extends AppCompatActivity {

    private TextView name;
    private TextView tag;
    private TextView price;
    private EditText notes;
    private ImageView photo;
    private MainApp mainApp;
    private TextView count;
    private Button reduce;
    private Button add;
    private Button car;
    private Entuty_Commodity entuty_commodity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_shop_commodity);
        mainApp = (MainApp) getApplication();
        photo = (ImageView) findViewById(R.id.photo);
        name = (TextView) findViewById(R.id.name);
        tag = (TextView) findViewById(R.id.tag);
        price = (TextView) findViewById(R.id.price);
        notes = (EditText) findViewById(R.id.notes);
        count = (TextView) findViewById(R.id.count);
        reduce = (Button) findViewById(R.id.reduce);
        add = (Button) findViewById(R.id.add);
        car = (Button) findViewById(R.id.car);
        entuty_commodity = mainApp.getEntuty_commodity();
        new use.ImageLoad(photo,entuty_commodity.getCommodity_photo()).execute();
        String label[] = {"餐點名稱","標籤","價格","備註"};
        String value[] = {entuty_commodity.getCommodity_name(),entuty_commodity.getCommodity_tag(),entuty_commodity.getCommodity_price(),entuty_commodity.getCommodity_notes()};
        name.setText(label[0]+":"+value[0]);
        tag.setText(label[3]+":"+value[3]);
        price.setText(value[2]);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countNumber = Integer.parseInt(count.getText().toString())+1;
                count.setText(Integer.toString(countNumber));
                if(countNumber==2)
                {
                    reduce.setEnabled(true);
                }
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countNumber = Integer.parseInt(count.getText().toString())-1;
                count.setText(Integer.toString(countNumber));
                if(countNumber==1)
                {
                    reduce.setEnabled(false);
                }
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainApp.getType()==0)
                {
                    Intent intent = new Intent(Browse_Shop_Commodity.this,LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    String[] field = new String[5];
                    field[0] = "uID";
                    field[1] = "sID";
                    field[2] = "fID";
                    field[3] = "user_notes";
                    field[4] = "amount";
                    String[] data = new String[5];
                    data[0] = Integer.toString(mainApp.getEntuty_user().getID());
                    data[1] = Integer.toString(mainApp.getEntuty_shop().getID());
                    data[2] = Integer.toString(mainApp.getEntuty_commodity().getID());
                    data[3] = (notes.getText().toString());
                    data[4] = count.getText().toString();
                    String result = use.getResult("http://20.187.122.219/users/cart/insertCart.php", field, data);
                    finish();
                }
            }
        });
    }

}