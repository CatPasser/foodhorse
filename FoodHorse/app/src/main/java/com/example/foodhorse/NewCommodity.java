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
import android.widget.EditText;
import android.widget.ImageView;
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

import pub.devrel.easypermissions.EasyPermissions;

public class NewCommodity extends AppCompatActivity {
    private EditText name;
    private EditText tag;
    private EditText price;
    private EditText notes;
    private ImageView photo;
    private TextView NewBT;
    private MainApp mainApp;
    private String upLoadServerUri = null;

    private Bitmap bitmap;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_commodity);
        NewBT = (TextView) findViewById(R.id.NewBT);
        mainApp = (MainApp) getApplication();
        photo = (ImageView) findViewById(R.id.photo);
        name = (EditText) findViewById(R.id.name);
        tag = (EditText) findViewById(R.id.tag);
        price = (EditText) findViewById(R.id.price);
        notes=(EditText) findViewById(R.id.notes);
        photo.setImageResource(R.drawable.img);
        upLoadServerUri = "http://20.187.122.219/photo/uploadFood.php";
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
        NewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
                NewBT();
            }
        });
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
    public void NewBT()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(NewCommodity.this);
        String[] field = new String[5];
        field[0] = "sID";
        field[1] = "foodname";
        field[2] = "tag";
        field[3] = "price";
        field[4] = "notes";
        String[] data = new String[5];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        data[1] = name.getText().toString();
        data[2] = tag.getText().toString();
        data[3] = price.getText().toString();
        data[4] = notes.getText().toString();
        String result = use.getResult("http://20.187.122.219/shop/food/putOn.php", field, data);
        if (result.equals("Put On Success")){
            builder.setTitle("Nice");
            builder.setMessage("上架成功!");
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(result.equals("Duplicate Food"))
        {
            builder.setTitle("error");
            builder.setMessage("商品名稱重複");
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

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