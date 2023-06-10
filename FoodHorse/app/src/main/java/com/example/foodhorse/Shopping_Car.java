package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Shopping_Car extends AppCompatActivity {
    private shopping_car_listadarter mListAdapter;
    private ListView list_view;
    private CheckBox checked;
    private TextView count;
    private Button pay;
    private MainApp mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        mainApp = (MainApp) getApplication();
        list_view = (ListView) findViewById(R.id.list_view);
        checked = (CheckBox) findViewById(R.id.checked);
        count = (TextView) findViewById(R.id.count);
        pay = (Button) findViewById(R.id.pay);


        String[] field = new String[1];
        field[0] = "uID";
        String[] data = new String[1];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        String result = use.getResult("http://20.187.122.219/users/cart/selectCart.php", field, data);
        String array[] = result.split("&&");//foodname photo price amount cID sID
        List<Entuty_Commodity> commodities = new ArrayList<>();
        List<String> amount = new ArrayList<>();
        List<String> cID = new ArrayList<>();
        List<Boolean> ch = new ArrayList<>();
        List<String> sID = new ArrayList<>();
        if(!result.equals("No Data")) {
            for (int i2 = 0; i2 < array.length; i2++) {
                String array2[] = array[i2].split(",");
                commodities.add(new Entuty_Commodity(array2[0], null, array2[2], null, array2[1]));
                amount.add(array2[3]);
                cID.add(array2[4]);
                sID.add(array2[5]);
                ch.add(false);
            }
        }
        mListAdapter = new shopping_car_listadarter(getApplicationContext(),commodities,Shopping_Car.this,amount,cID,Integer.toString(mainApp.getEntuty_user().getID()),ch);
        list_view.setAdapter(mListAdapter);

        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checked.isChecked())
                {
                    mListAdapter.all();
                    mListAdapter.notifyDataSetChanged();
                }
                else
                {
                    mListAdapter.nall();
                    mListAdapter.notifyDataSetChanged();
                }
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> ID = new ArrayList<>();
                List<String> amount = new ArrayList<>();
                boolean two = false;
                String  csID="0";
                int t=0;
                for(int i =0;i<mListAdapter.getCount();i++)
                {
                    //CheckBox ck = list_view.getChildAt(i).findViewById(R.id.checked);
                    List<Boolean> ch = mListAdapter.getCh();
                    if(ch.get(i))
                    {
                        if(!csID.equals("0")&&!csID.equals(sID.get(i)))
                        {
                            two = true;
                        }
                        ID.add(cID.get(i));
                        csID = sID.get(i);
                        //TextView tv = list_view.getChildAt(i).findViewById(R.id.count);
                        amount.add("");
                        t++;
                    }
                }
                String[] field = new String[2];
                field[0] = "cID";
                field[1] = "amount";
                String[] data = new String[2];
                for(int i = 0;i<t;i++)
                {
                    data[0] = ID.get(i);
                    data[1] = amount.get(i);
                    String result = use.getResult("http://20.187.122.219/users/cart/updateCart.php", field, data);
                }
                Intent intent = new Intent(Shopping_Car.this,shopping_checkout.class);
                intent.putExtra("amount",t);
                for(int i = 0;i<t;i++)
                {
                    intent.putExtra(Integer.toString(i),ID.get(i));
                }
                if (two)
                {
                    Toast.makeText(Shopping_Car.this, "不同店家請分開結帳", Toast.LENGTH_LONG).show();
                }
                else if(t!=0) {
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Shopping_Car.this, "尚未選擇商品", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}