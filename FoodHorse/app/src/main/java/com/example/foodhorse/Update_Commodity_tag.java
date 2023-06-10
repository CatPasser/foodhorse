package com.example.foodhorse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Update_Commodity_tag extends AppCompatActivity {

    private EditText value;
    private MainApp mainApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_commodity_tag);
        value = findViewById(R.id.value);
        mainApp = (MainApp) getApplication();
    }

    public void ENTER(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Update_Commodity_tag.this);
        String[] field = new String[3];
        field[0] = "sID";
        field[1] = "foodname";
        field[2] = "newtag";
        String[] data = new String[3];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        data[1] = mainApp.getEntuty_commodity().getCommodity_name();
        data[2] = value.getText().toString();
        String result = use.getResult("http://20.187.122.219/shop/food/update/tagName.php", field, data);
        if (result.equals("Update Success")){
            Entuty_Commodity commodity = mainApp.getEntuty_commodity();
            commodity.setCommodity_tag(value.getText().toString());
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