package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Browse_Shop extends AppCompatActivity {

    private ImageView imageView;
    private Spinner spinner;
    private ListView listView;
    private listlayoutadapter3 listlayoutadapter;
    private TextView shopname;
    private TextView evaluation;
    int x = 0;
    String tag ="";
    String tagarray[];
    private MainApp mainApp;
    private Entuty_Shop entuty_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_shop);
        mainApp = (MainApp) getApplication();
        imageView = (ImageView) findViewById(R.id.image);
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.list_view);
        shopname = (TextView) findViewById(R.id.shopname);
        evaluation = (TextView) findViewById(R.id.evaluation);

        entuty_shop = mainApp.getEntuty_shop();
        String[] field = new String[1];
        field[0] = "sID";
        String[] data = new String[1];
        data[0] = Integer.toString(entuty_shop.getID());
        String result = use.getResult("http://20.187.122.219/home/shop/shopInfo.php", field, data);
        if (!result.equals("No Data")){
            String array[] = result.split(",");//name phone address photo evaluation
            entuty_shop.setName(array[0]);
            entuty_shop.setPhone(array[1]);
            entuty_shop.setAddress(array[2]);
            entuty_shop.setPhoto(array[3]);
            entuty_shop.setEvaluation(array[4]);
            mainApp.setEntuty_shop(entuty_shop);
            shopname.setText(entuty_shop.getName());
            new use.ImageLoad(imageView,entuty_shop.getPhoto()).execute();
            evaluation.setText(entuty_shop.getEvaluation()+"★");
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainApp.setEntuty_commodity(listlayoutadapter.getCommodity(i));
                Intent intent = new Intent(Browse_Shop.this,Browse_Shop_Commodity.class);
                startActivity(intent);

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                x = spinner.getSelectedItemPosition();
                if (x == 0)
                {
                    String[] field = new String[1];
                    field[0] = "sID";
                    String[] data = new String[1];
                    data[0] = Integer.toString(entuty_shop.getID());
                    String result = use.getResult("http://20.187.122.219/shop/food/display/allDisplay.php", field, data);
                    if (!result.equals("No Data")){
                        String array[] = result.split("&&");//tag foodname photo price notes time up id
                        List<Entuty_Commodity> commodities = new ArrayList<>();
                        for (int i2 = 0; i2 < array.length; i2++) {
                            String array2[] = array[i2].split(",");
                            commodities.add(new Entuty_Commodity(array2[1],array2[0],array2[3],array2[4],array2[2],Integer.parseInt(array2[7])));
                        }
                        listlayoutadapter = new listlayoutadapter3(getApplicationContext(),commodities);
                        listView.setAdapter(listlayoutadapter);
                    }
                }
                else
                {
                    tag = tagarray[x];
                    String[] field = new String[2];
                    field[0] = "sID";
                    field[1] = "tag";
                    String[] data = new String[2];
                    data[0] = Integer.toString(entuty_shop.getID());
                    data[1] = tag;
                    String result = use.getResult("http://20.187.122.219/shop/food/display/tagDisplay.php", field, data);
                    String array[] = result.split("&&");//tag foodname photo price notes time up id
                    List<Entuty_Commodity> commodities = new ArrayList<>();
                    for (int i2 = 0; i2 < array.length; i2++) {
                        String array2[] = array[i2].split(",");
                        commodities.add(new Entuty_Commodity(array2[1],array2[0],array2[3],array2[4],array2[2],Integer.parseInt(array2[7])));
                    }
                    listlayoutadapter = new listlayoutadapter3(getApplicationContext(),commodities);

                    listView.setAdapter(listlayoutadapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        String[] field2 = new String[1];
        field2[0] = "sID";
        String[] data2 = new String[1];
        data2[0] =  Integer.toString(entuty_shop.getID());
        String result2 = use.getResult("http://20.187.122.219/shop/food/display/tagSelect.php", field, data);
        if (!result2.equals("No Data")){
            result2 = "全部," + result2;
        } else{
            result2 = "全部";
        }
        tagarray = result2.split(",");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tagarray);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }
}