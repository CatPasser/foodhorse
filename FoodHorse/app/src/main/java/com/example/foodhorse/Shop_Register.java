package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Shop_Register extends AppCompatActivity {

    private EditText inputAccount;
    private EditText inputPasssword;
    private EditText inpputPhone;
    private EditText inputName;
    private EditText inputVerify;
    private String Account;
    private String Password;
    private String Phone;
    private String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);
        inputAccount =(EditText)findViewById(R.id.account);
        inputPasssword = (EditText) findViewById(R.id.password);
        inpputPhone = (EditText) findViewById(R.id.phone);
        inputVerify = (EditText) findViewById(R.id.verify);
        inputName =(EditText) findViewById(R.id.name);
    }
    public void register(View view) {
        Account = inputAccount.getText().toString();
        Password = inputPasssword.getText().toString();
        Phone = inpputPhone.getText().toString();
        Name = inputName.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(Shop_Register.this);
        if(Account.equals("") || Password.equals("") || Phone.equals("") || Name.equals(""))
        {
            builder.setTitle("error");
            builder.setMessage("請輸入全部資訊");
            builder.show();
        }
        else
        {
            String[] field = new String[4];
            field[0] = "account";
            field[1] = "password";
            field[2] = "phone";
            field[3] = "fullname";
            String[] data = new String[4];
            data[0] = Account;
            data[1] = Password;
            data[2] = Phone;
            data[3] = Name;
            String result = use.getResult("http://20.187.122.219/shop/signup.php", field, data);
            if (result.equals("Sign up Success")){
                builder.setTitle("Nice");
                builder.setMessage("註冊店家帳戶成功!");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else if (result.equals("duplicate account")){
                builder.setTitle("error");
                builder.setMessage("已有此帳號");
                builder.show();
            }
            else {
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        }

    }
}