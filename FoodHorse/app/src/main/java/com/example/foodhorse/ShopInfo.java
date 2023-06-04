package com.example.foodhorse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopInfo extends AppCompatActivity {

    private TextView sID;
    private TextView shopname;
    private TextView phone;
    private TextView address;
    private TextView password;
    private ImageView photo;
    private String upLoadServerUri = null;
    private Entuty_User entuty_user;

    private Spinner spinner;
    private String tagarray[] = {"標籤1","標籤2","標籤3"};
    private Button update_tag;
    private boolean update = false;

    private Bitmap bitmap;
    MainApp mainApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);
        mainApp = (MainApp) getApplication();
        entuty_user = mainApp.getEntuty_user();
        photo = (ImageView) findViewById(R.id.photo);
        sID = findViewById(R.id.sID);
        shopname = findViewById(R.id.shopname);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        String label[] = {"ID","店名","電話","地址","密碼"};
        String value[] = {Integer.toString(entuty_user.getID()),entuty_user.getName(),entuty_user.getPhone(),entuty_user.getAddress(),"*****"};
        sID.setText(label[0]+":   "+value[0]);
        shopname.setText(label[1]+":   "+value[1]);
        phone.setText(label[2]+":   "+value[2]);
        address.setText(label[3]+":   "+value[3]);
        password.setText(label[4]+":   "+value[4]);
        new use.ImageLoad(photo,entuty_user.getPhoto()).execute();
        upLoadServerUri = "http://20.187.122.219/photo/uploadShop.php";
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setEnabled(false);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tagarray);
        spinner.setAdapter(adapter);
        switch (entuty_user.getTag())
        {
            case "標籤1":
                spinner.setSelection(0);
                break;
            case "標籤2":
                spinner.setSelection(1);
                break;
            case "標籤3":
                spinner.setSelection(2);
                break;
        }
        update_tag = (Button)findViewById(R.id.updata_tag);
        update_tag.setText("修改標籤");
        update_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    String[] field = new String[2];
                    field[0] = "shopname";
                    field[1] = "tag";
                    String[] data = new String[2];
                    data[0] = entuty_user.getAccount();
                    data[1] = (String) spinner.getSelectedItem();
                    String result = use.getResult("http://20.187.122.219/shop/update/tag.php", field, data);
                    if (result.equals("Update Success")){
                        builder.setTitle("Nice");
                        builder.setMessage("修改成功!");
                        entuty_user.setTag((String) spinner.getSelectedItem());
                        mainApp.setEntuty_user(entuty_user);
                        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    update = false;
                    spinner.setEnabled(false);
                    update_tag.setText("修改標籤");
                }
                else
                {
                    update = true;
                    spinner.setEnabled(true);
                    update_tag.setText("確認修改");
                }
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                                photo.setImageBitmap(bitmap);
                                uploadImage();
                                updatephoto();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        shopname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopInfo.this,Update_Name.class);
                startActivity(intent);
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopInfo.this,Update_Address.class);
                startActivity(intent);
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopInfo.this,Update_Password.class);
                startActivity(intent);
            }
        });
    }
    public void updatephoto()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShopInfo.this);
        String[] field = new String[1];
        field[0] = "shopname";
        String[] data = new String[1];
        data[0] = entuty_user.getAccount();
        String result = use.getResult("http://20.187.122.219/shop/update/photo.php", field, data);
        entuty_user.setPhoto(result);
        mainApp.setEntuty_user(entuty_user);
        builder.setTitle("Nice");
        builder.setMessage("更新成功!");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void uploadImage(){
        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        if(bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.WEBP,10,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes,Base64.DEFAULT);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = upLoadServerUri;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> paramV = new HashMap<>();
                    paramV.put("image", base64Image);
                    return paramV;
                }
            };
            queue.add(stringRequest);
        }
        else Toast.makeText(getApplicationContext(),"Select the image first",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        String label[] = {"ID","店名","電話","地址","密碼"};
        String value[] = {Integer.toString(entuty_user.getID()),entuty_user.getName(),entuty_user.getPhone(),entuty_user.getAddress(),"*****"};
        sID.setText(label[0]+":   "+value[0]);
        shopname.setText(label[1]+":   "+value[1]);
        phone.setText(label[2]+":   "+value[2]);
        address.setText(label[3]+":   "+value[3]);
        password.setText(label[4]+":   "+value[4]);
    }
}