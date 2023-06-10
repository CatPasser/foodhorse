package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Update_Name extends AppCompatActivity {

    private EditText value;
    private MainApp mainApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        value = findViewById(R.id.value);
        mainApp = (MainApp) getApplication();
    }
    public void ENTER(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Update_Name.this);
        String name = value.getText().toString();
        if(mainApp.getType()==1)
        {
            String[] field = new String[2];
            field[0] = "username";
            field[1] = "fullname";
            String[] data = new String[2];
            data[0] = mainApp.getEntuty_user().getAccount();
            data[1] = name;
            String result = use.getResult("http://20.187.122.219/users/update/fullname.php", field, data);
            if (result.equals("Update Success")){
                Entuty_User user = mainApp.getEntuty_user();
                user.setName(name);
                mainApp.setEntuty_user(user);
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
            field[1] = "fullname";
            String[] data = new String[2];
            data[0] = mainApp.getEntuty_user().getAccount();
            data[1] = name;
            String result = use.getResult("http://20.187.122.219/shop/update/fullname.php", field, data);
            if (result.equals("Update Success")){
                Entuty_User user = mainApp.getEntuty_user();
                user.setName(name);
                mainApp.setEntuty_user(user);
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
        else if(mainApp.getType()==3)
        {
            String[] field = new String[2];
            field[0] = "horsename";
            field[1] = "fullname";
            String[] data = new String[2];
            data[0] = mainApp.getEntuty_user().getAccount();
            data[1] = name;
            String result = use.getResult("http://20.187.122.219/horse/update/fullname.php", field, data);
            if (result.equals("Update Success")){
                Entuty_User user = mainApp.getEntuty_user();
                user.setName(name);
                mainApp.setEntuty_user(user);
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