package com.example.foodhorse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ImageView;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class use {
    static String result;
    public static String getResult(String url,String[] field,String[] data)
    {
        result="";
        PutData putData = new PutData(url, "POST", field, data);
        if (putData.startPut())
        {
            if (putData.onComplete())
            {
                result = new String(putData.getResult().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
                return result;
            }
        }
        return result;
    }

    public static class ImageLoad extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        String url;

        public ImageLoad(ImageView imageView,String url) {
            this.imageView = imageView;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return getBitmapFromURL(url);
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    //讀取網路圖片，型態為Bitmap
    public static Bitmap getBitmapFromURL(String imageUrl)
    {
        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
