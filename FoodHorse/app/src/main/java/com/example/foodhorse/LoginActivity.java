package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


public class LoginActivity extends AppCompatActivity {
    private MainApp mainApp;
    private EditText inputAccount;
    private EditText inputPasssword;
    private String Account;
    private String Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainApp = (MainApp) getApplication();
        inputAccount =(EditText)findViewById(R.id.account);
        inputPasssword = (EditText) findViewById(R.id.password);
    }

    public void login(View view)
    {
        Account =inputAccount.getText().toString();
        Password=inputPasssword.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        if(Account.equals("") || Password.equals(""))
        {

            builder.setTitle("error");
            builder.setMessage("請輸入帳號及密碼");
            builder.show();
        }
        else
        {
            RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg1);
            int rb_id = rg1.getCheckedRadioButtonId();
            if(rb_id ==-1)
            {
                builder.setTitle("error");
                builder.setMessage("請選擇帳戶類型");
                builder.show();
            }
            else if(rb_id == R.id.rb1)
            {
                String[] field = new String[2];
                field[0] = "account";
                field[1] = "password";
                String[] data = new String[2];
                data[0] = Account;
                data[1] = Password;
                String result = use.getResult("http://20.187.122.219/users/login.php", field, data);
                String array[] = result.split(",");
                if (array[0].equals("Login Success")){
                    mainApp.setType(1);
                    Entuty_User entuty_user = new Entuty_User(Integer.parseInt(array[1]),array[2],array[3],array[4],array[5],null,null);
                    mainApp.setEntuty_user(entuty_user);
                    builder.setTitle("Nice");
                    builder.setMessage("登入使用者帳戶成功");
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
                    builder.setMessage("帳號或密碼錯誤");
                    builder.show();
                }
            }
            else if(rb_id == R.id.rb2)
            {
                String[] field = new String[2];
                field[0] = "account";
                field[1] = "password";
                String[] data = new String[2];
                data[0] = Account;
                data[1] = Password;
                String result = use.getResult("http://20.187.122.219/shop/login.php", field, data);
                String array[] = result.split(",");
                if (array[0].equals("Login Success")){
                    mainApp.setType(2);
                    Entuty_User entuty_user = new Entuty_User(Integer.parseInt(array[1]),array[2],array[3],array[4],array[5],array[6],array[7]);
                    mainApp.setEntuty_user(entuty_user);
                    builder.setTitle("Nice");
                    builder.setMessage("登入店家帳戶成功");
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
                    builder.setMessage("帳號或密碼錯誤");
                    builder.show();
                }
            }
            else if(rb_id == R.id.rb3)
            {
                String[] field = new String[2];
                field[0] = "account";
                field[1] = "password";
                String[] data = new String[2];
                data[0] = Account;
                data[1] = Password;
                String result = use.getResult("http://20.187.122.219/horse/login.php", field, data);
                String array[] = result.split(",");
                if (array[0].equals("Login Success")){
                    mainApp.setType(3);
                    Entuty_User entuty_user = new Entuty_User(Integer.parseInt(array[1]),array[2],array[3],array[4],null,null,null);
                    mainApp.setEntuty_user(entuty_user);
                    builder.setTitle("Nice");
                    builder.setMessage("登入外送員帳戶成功");
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
                    builder.setMessage("帳號或密碼錯誤");
                    builder.show();
                }
            }


        }
    }
    public void register(View view)
    {
        Account =inputAccount.getText().toString();
        Password=inputPasssword.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg1);
        int rb_id = rg1.getCheckedRadioButtonId();
        if(rb_id ==-1)
        {
            builder.setTitle("error");
            builder.setMessage("請選擇帳戶類型");
            builder.show();
        }
        else if(rb_id == R.id.rb1)
        {

            Intent intent = new Intent(this,User_Register.class);
            startActivity(intent);
        }
        else if(rb_id == R.id.rb2)
        {

            Intent intent = new Intent(this,Shop_Register.class);
            startActivity(intent);
        }
        else if(rb_id == R.id.rb3)
        {

            Intent intent = new Intent(this,Driver_Register.class);
            startActivity(intent);
        }
    }
}