package com.example.foodhorse;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class Driver_Shop_Order_listadapter extends BaseAdapter {

    private List<String> addresslist;
    private LayoutInflater mLayInf;
    private Context context;
    private List<String> oIDlist;
    private String hID;
    private Driver_Shop_Order driver_shop_order;
    public Driver_Shop_Order_listadapter(Context context,List<String> address,List<String> oID,String hID,Driver_Shop_Order driver_shop_order,Context context2)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addresslist = address;
        oIDlist = oID;
        this.hID = hID;
        this.context = context2;
        this.driver_shop_order = driver_shop_order;
    }

    @Override
    public int getCount() {return addresslist.size();}

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mLayInf.inflate(R.layout.driver_shop_order_list,viewGroup,false);
        TextView order = (TextView) v.findViewById(R.id.order);
        TextView address = (TextView) v.findViewById(R.id.address);
        Button bt = (Button) v.findViewById(R.id.bt);
        order.setText("訂單"+Integer.toString(i));
        address.setText(addresslist.get(i));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("接取訂單");
                builder.setMessage("確定要接取這個訂單嗎");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        String[] field = new String[2];
                        field[0] = "oID";
                        field[1] = "hID";
                        String[] data = new String[2];
                        data[0] = oIDlist.get(i);
                        data[1] = hID;
                        String result = use.getResult("http://20.187.122.219/horse/orders/getUserOrder.php", field, data);//Success
                        if (result.equals("Success")) {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                            builder2.setTitle("Nice!");
                            builder2.setMessage("接單成功");
                            AlertDialog dialog2 = builder2.create();
                            dialog2.show();
                            driver_shop_order.close(oIDlist.get(i),hID);
                        }
                        else if(result.equals("Already Taken"))
                        {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                            builder2.setTitle("error");
                            builder2.setMessage("此訂單已被接");
                            AlertDialog dialog2 = builder2.create();
                            dialog2.show();
                            driver_shop_order.onResume();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return v;
    }
}
