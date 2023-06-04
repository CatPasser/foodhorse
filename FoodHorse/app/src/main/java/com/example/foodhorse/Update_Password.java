package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Update_Password extends AppCompatActivity {

    private EditText value;
    private EditText value2;
    private EditText old;
    private MainApp mainApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        value = findViewById(R.id.value);
        value2 = findViewById(R.id.value2);
        mainApp = (MainApp) getApplication();
        old = findViewById(R.id.old);
    }
    public void ENTER(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Update_Password.this);
        String password = value.getText().toString();
        String password2 = value2.getText().toString();
        String oldpassword = old.getText().toString();
        if(password.equals(password2)) {
            if (mainApp.getType() == 1) {
                String[] field = new String[3];
                field[0] = "username";
                field[1] = "password";
                field[2] = "newpassword";
                String[] data = new String[3];
                data[0] = mainApp.getEntuty_user().getAccount();
                data[1] = oldpassword;
                data[2] = password;
                String result = use.getResult("http://20.187.122.219/users/update/password.php", field, data);
                if (result.equals("Update Success")) {
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
                } else if (result.equals("Wrong Password")) {
                    builder.setTitle("error");
                    builder.setMessage("密碼錯誤");
                    builder.show();
                } else {
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
            } else if (mainApp.getType() == 2) {
                String[] field = new String[3];
                field[0] = "shopname";
                field[1] = "password";
                field[2] = "newpassword";
                String[] data = new String[3];
                data[0] = mainApp.getEntuty_user().getAccount();
                data[1] = oldpassword;
                data[2] = password;
                String result = use.getResult("http://20.187.122.219/shop/update/password.php", field, data);
                if (result.equals("Update Success")) {
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
                } else if (result.equals("Wrong Password")) {
                    builder.setTitle("error");
                    builder.setMessage("密碼錯誤");
                    builder.show();
                } else {
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
            } else if (mainApp.getType() == 3) {
                String[] field = new String[3];
                field[0] = "horsename";
                field[1] = "password";
                field[2] = "newpassword";
                String[] data = new String[3];
                data[0] = mainApp.getEntuty_user().getAccount();
                data[1] = oldpassword;
                data[2] = password;
                String result = use.getResult("http://20.187.122.219/horse/update/password.php", field, data);
                if (result.equals("Update Success")) {
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
                } else if (result.equals("Wrong Password")) {
                    builder.setTitle("error");
                    builder.setMessage("密碼錯誤");
                    builder.show();
                } else {
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
        else{
            builder.setTitle("error");
            builder.setMessage("兩次新密碼不同,請再試一次");
            builder.show();
        }
        /*if(mainApp.getUser()!=null && mainApp.getUser().Password.equals(oldpassword))
        {
            User_Room user = mainApp.getUser();
            user.setPassword(password);
            User_RoomDatabase userDatabase = Room.databaseBuilder(getApplicationContext(),User_RoomDatabase.class,"User_Database").allowMainThreadQueries().build();
            User_RoomDao userDao = userDatabase.getDao();
            userDao.update(user);
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
        else if(mainApp.getShop()!=null && mainApp.getShop().Password.equals(oldpassword))
        {
            Shop_Room shop = mainApp.getShop();
            shop.setPassword(password);
            Shop_RoomDatabase shopDatabase = Room.databaseBuilder(getApplicationContext(),Shop_RoomDatabase.class,"Shop_Database").allowMainThreadQueries().build();
            Shop_RoomDao shopDao = shopDatabase.getDao();
            shopDao.update(shop);
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
        else if(mainApp.getDriver()!=null && mainApp.getDriver().Password.equals(oldpassword))
        {
            Driver_Room driver = mainApp.getDriver();
            driver.setPassword(password);
            Driver_RoomDatabase driverDatabase = Room.databaseBuilder(getApplicationContext(),Driver_RoomDatabase.class,"Driver_Database").allowMainThreadQueries().build();
            Driver_RoomDao driverDao = driverDatabase.getDao();
            driverDao.update(driver);
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
        else
        {
            builder.setTitle("error");
            builder.setMessage("密碼錯誤");
            builder.show();
        }*/
    }
}