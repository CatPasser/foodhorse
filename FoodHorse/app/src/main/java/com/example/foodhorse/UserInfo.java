package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {

    private TextView uID;
    private TextView username;
    private TextView phone;
    private TextView address;
    private TextView password;
    MainApp mainApp;
    private Entuty_User entuty_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mainApp = (MainApp) getApplication();
        entuty_user = mainApp.getEntuty_user();
        uID = findViewById(R.id.uID);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        String label[] = {"ID","姓名","電話","地址","密碼"};
        String value[] = {Integer.toString(entuty_user.getID()),entuty_user.getName(),entuty_user.getPhone(),entuty_user.getAddress(),"*****"};
        uID.setText(label[0]+":   "+value[0]);
        username.setText(label[1]+":   "+value[1]);
        phone.setText(label[2]+":   "+value[2]);
        address.setText(label[3]+":   "+value[3]);
        password.setText(label[4]+":   "+value[4]);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfo.this,Update_Name.class);
                startActivity(intent);
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfo.this,Update_Address.class);
                startActivity(intent);
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfo.this,Update_Password.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String label[] = {"ID","姓名","電話","地址","密碼"};
        String value[] = {Integer.toString(entuty_user.getID()),entuty_user.getName(),entuty_user.getPhone(),entuty_user.getAddress(),"*****"};
        uID.setText(label[0]+":   "+value[0]);
        username.setText(label[1]+":   "+value[1]);
        phone.setText(label[2]+":   "+value[2]);
        address.setText(label[3]+":   "+value[3]);
        password.setText(label[4]+":   "+value[4]);
    }
}