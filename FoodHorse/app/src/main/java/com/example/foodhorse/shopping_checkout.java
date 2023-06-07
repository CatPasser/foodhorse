package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class shopping_checkout extends AppCompatActivity {


    private MainApp mainApp;
    private shopping_checkout_listadarter mListAdapter;
    private ListView list_view;
    private EditText address;
    private EditText notes;
    private TextView count;
    private Button send;
    private List<String> ID = new ArrayList<>();
    private int total=0;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_checkout);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        mainApp = (MainApp) getApplication();
        list_view = (ListView) findViewById(R.id.list_view);
        address = (EditText) findViewById(R.id.address);
        notes = (EditText) findViewById(R.id.notes);
        count = (TextView) findViewById(R.id.count);
        send = (Button) findViewById(R.id.send);

        getLastLocation();
        //address.setText(mainApp.getEntuty_user().getAddress());
        Intent intent = getIntent();
        for(int i=0;i<intent.getIntExtra("amount",0);i++)
        {
            ID.add(intent.getStringExtra(Integer.toString(i)));
        }
        String[] field = new String[1];
        field[0] = "cID";
        String[] data = new String[1];
        data[0] = ID.get(0);
        for (int i=1;i< ID.size();i++)
        {
            data[0] += ","+ID.get(i);
        }
        String result = use.getResult("http://20.187.122.219/users/cart/check.php", field, data);
        String array[] = result.split("&&");//第一個sID 後面photo name price amount notes
        String sID = array[0];
        List<Entuty_Commodity> commodities = new ArrayList<>();
        List<String> amount = new ArrayList<>();
        for (int i2 = 1; i2 < array.length; i2++) {
            String array2[] = array[i2].split(",");
            commodities.add(new Entuty_Commodity(array2[1],null,array2[2],array2[4],array2[0]));
            amount.add(array2[3]);
            total+=Integer.parseInt(array2[3])*Integer.parseInt(array2[2]);
        }

        mListAdapter = new shopping_checkout_listadarter(getApplicationContext(),commodities,amount);
        list_view.setAdapter(mListAdapter);
        TextView textView = new TextView(this);
        textView.setText("結帳");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        list_view.addHeaderView(textView);
        count.setText("總金額："+Integer.toString(total));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] field = new String[6];
                field[0] = "uID";
                field[1] = "total_price";
                field[2] = "cID";
                field[3] = "sID";
                field[4] = "location";
                field[5] = "notes";
                String[] data = new String[6];
                data[0] = Integer.toString(mainApp.getEntuty_user().getID());
                data[1] = Integer.toString(total);
                data[2] = ID.get(0);
                data[3] = sID;
                data[4] = address.getText().toString();
                data[5] = notes.getText().toString();
                for (int i=1;i< ID.size();i++)
                {
                    data[2] += ","+ID.get(i);
                }
                String result = use.getResult("http://20.187.122.219/users/orders/buy.php", field, data);
                finish();
            }
        });
    }
    private void getLastLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null){
                                Geocoder geocoder = new Geocoder(shopping_checkout.this, Locale.getDefault());
                                List<Address> addresses = null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    address.setText(addresses.get(0).getAddressLine(0));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    });
        }
        else{
            askPermission();
        }
    }
    private void askPermission(){
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},100);
    }
}