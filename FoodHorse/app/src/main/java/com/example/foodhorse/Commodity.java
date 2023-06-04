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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Commodity extends AppCompatActivity {


    private ImageView imageView;
    private Spinner spinner;
    private ListView listView;
    private listlayoutadapter listlayoutadapter;
    private MainApp mainApp;
    private Entuty_User entuty_user;
    int x = 0;
    String tag ="";
    String tagarray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity);
        mainApp = (MainApp) getApplication();
        entuty_user = mainApp.getEntuty_user();
        imageView = (ImageView) findViewById(R.id.image);
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.list_view);

        new use.ImageLoad(imageView,entuty_user.getPhoto()).execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mainApp.setEntuty_commodity(listlayoutadapter.getCommodity(i));
                Intent intent = new Intent(Commodity.this,CommodityInfo.class);
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
                    data[0] = Integer.toString(entuty_user.getID());
                    String result = use.getResult("http://20.187.122.219/shop/food/display/allDisplay.php", field, data);
                    if (!result.equals("No Data")){
                        String array[] = result.split("&&");//tag foodname photo price time up
                        List<Entuty_Commodity> commodities = new ArrayList<>();
                        for (int i2 = 0; i2 < array.length; i2++) {
                            String array2[] = array[i2].split(",");
                            commodities.add(new Entuty_Commodity(array2[1],array2[0],array2[3],array2[4],array2[2],array2[5],array2[6]));
                        }
                        listlayoutadapter = new listlayoutadapter(getApplicationContext(),commodities);
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
                    data[0] = Integer.toString(entuty_user.getID());
                    data[1] = tag;
                    String result = use.getResult("http://20.187.122.219/shop/food/display/tagDisplay.php", field, data);
                    String array[] = result.split("&&");//tag foodname photo price time up
                    List<Entuty_Commodity> commodities = new ArrayList<>();
                    for (int i2 = 0; i2 < array.length; i2++) {
                        String array2[] = array[i2].split(",");
                        commodities.add(new Entuty_Commodity(array2[1],array2[0],array2[3],array2[4],array2[2],array2[5],array2[6]));
                    }
                    listlayoutadapter = new listlayoutadapter(getApplicationContext(),commodities);
                    listView.setAdapter(listlayoutadapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        String[] field = new String[1];
        field[0] = "sID";
        String[] data = new String[1];
        data[0] = Integer.toString(entuty_user.getID());
        String result = use.getResult("http://20.187.122.219/shop/food/display/tagSelect.php",field, data);
        if (!result.equals("No Data")){
            result = "全部," + new String(result.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        } else{
            result = "全部";
        }
        tagarray = result.split(",");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tagarray);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

    }

    public void NewBT(View view)
    {
        Intent intent = new Intent(this,NewCommodity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String[] field = new String[1];
        field[0] = "sID";
        String[] data = new String[1];
        data[0] = Integer.toString(entuty_user.getID());
        String result = use.getResult("http://20.187.122.219/shop/food/display/tagSelect.php", field, data);
        if (!result.equals("No Data")){
            result = "全部," + result;
        } else{
            result = "全部";
        }
        tagarray = result.split(",");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tagarray);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }
}