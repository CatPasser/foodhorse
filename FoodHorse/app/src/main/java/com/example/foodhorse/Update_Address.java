package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Update_Address extends AppCompatActivity {

    private EditText value;
    private MainApp mainApp;
    private Entuty_User entuty_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        value = findViewById(R.id.value);
        mainApp = (MainApp) getApplication();
        entuty_user = mainApp.getEntuty_user();
    }
    public void ENTER(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Update_Address.this);
        String address = value.getText().toString();
        if(mainApp.getType()==1)
        {
            String[] field = new String[2];
            field[0] = "username";
            field[1] = "address";
            String[] data = new String[2];
            data[0] = entuty_user.getAccount();
            data[1] = address;
            String result = use.getResult("http://20.187.122.219/users/update/address.php", field, data);
            if (result.equals("Update Success")){
                entuty_user.setAddress(address);
                mainApp.setEntuty_user(entuty_user);
                builder.setTitle("Nice");
                builder.setMessage("修改成功");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                builder.setTitle("error");
                builder.setMessage(result);
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        else if(mainApp.getType()==2)
        {
            String[] field = new String[2];
            field[0] = "shopname";
            field[1] = "address";
            String[] data = new String[2];
            data[0] = entuty_user.getAccount();
            data[1] = address;
            String result = use.getResult("http://20.187.122.219/shop/update/address.php", field, data);
            if (result.equals("Update Success")){
                entuty_user.setAddress(address);
                mainApp.setEntuty_user(entuty_user);
                builder.setTitle("Nice");
                builder.setMessage("修改成功");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                builder.setTitle("error");
                builder.setMessage(result);
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}