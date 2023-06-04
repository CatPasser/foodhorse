package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DriverInfo extends AppCompatActivity {

    private TextView dID;
    private TextView drivername;
    private TextView phone;
    private TextView password;
    MainApp mainApp;
    private Entuty_User entuty_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_info);
        mainApp = (MainApp) getApplication();
        entuty_user = mainApp.getEntuty_user();
        dID = findViewById(R.id.dID);
        drivername = findViewById(R.id.drivername);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        String label[] = {"ID","姓名","電話","密碼"};
        String value[] = {Integer.toString(entuty_user.getID()),entuty_user.getName(),entuty_user.getPhone(),"*****"};
        dID.setText(label[0]+":   "+value[0]);
        drivername.setText(label[1]+":   "+value[1]);
        phone.setText(label[2]+":   "+value[2]);
        password.setText(label[3]+":   "+value[3]);

        drivername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverInfo.this,Update_Name.class);
                startActivity(intent);
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverInfo.this,Update_Password.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String label[] = {"ID","姓名","電話","密碼"};
        String value[] = {Integer.toString(entuty_user.getID()),entuty_user.getName(),entuty_user.getPhone(),"*****"};
        dID.setText(label[0]+":   "+value[0]);
        drivername.setText(label[1]+":   "+value[1]);
        phone.setText(label[2]+":   "+value[2]);
        password.setText(label[3]+":   "+value[3]);
    }
}