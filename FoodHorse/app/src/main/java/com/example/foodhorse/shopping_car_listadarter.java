package com.example.foodhorse;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class shopping_car_listadarter extends BaseAdapter {
    private List<Entuty_Commodity> commodityLiset;
    private LayoutInflater mLayInf;
    private String url;
    private Context context;
    private List<String> amount;
    private List<String> cID;
    private List<Boolean> ch;
    private String uID;

    public shopping_car_listadarter(Context context, List<Entuty_Commodity> commodityLiset ,Context context2,List<String> amount,List<String> cID,String uID,List<Boolean> ch)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.commodityLiset = commodityLiset;
        this.context = context2;
        this.amount = amount;
        this.cID = cID;
        this.uID = uID;
        this.ch = ch;
    }

    @Override
    public int getCount() {
        return commodityLiset.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mLayInf.inflate(R.layout.shopping_car_list,viewGroup,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView price = (TextView) v.findViewById(R.id.price);
        TextView count = (TextView) v.findViewById(R.id.count);
        Button reduce = (Button) v.findViewById(R.id.reduce);
        Button add = (Button) v.findViewById(R.id.add);
        final CheckBox checked = (CheckBox) v.findViewById(R.id.checked);
        count.setText(amount.get(i));

        checked.setChecked(ch.get(i));

        name.setText(commodityLiset.get(i).getCommodity_name());
        price.setText("$"+commodityLiset.get(i).getCommodity_price());
        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checked.isChecked())
                {
                    ch.set(i,true);
                }
                else
                {
                    ch.set(i,false);
                }
                notifyDataSetChanged();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countNumber = Integer.parseInt(count.getText().toString())+1;
                count.setText(Integer.toString(countNumber));
            }
        });
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countNumber = Integer.parseInt(count.getText().toString())-1;
                if(countNumber==0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("即將移除餐點");
                    builder.setMessage("確定要將餐點從購物車刪除嗎");
                    builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            //刪除餐點
                            String[] field = new String[2];
                            field[0] = "uID";
                            field[1] = "cID";
                            String[] data = new String[2];
                            data[0] = uID;
                            data[1] = cID.get(i);
                            String result = use.getResult("http://20.187.122.219/users/cart/deleteCart.php", field, data);
                            count.setText(Integer.toString(countNumber));
                            commodityLiset.remove(i);
                            amount.remove(i);
                            ch.remove(i);
                            notifyDataSetChanged();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    count.setText(Integer.toString(countNumber));
                }

            }
        });


        url = commodityLiset.get(i).getCommodity_photo();
        new use.ImageLoad(imageView,url).execute();

        return v;
    }
    public void all()
    {
        for (int i = 0 ;i<ch.size();i++)
        {
            ch.set(i,true);
        }
        notifyDataSetChanged();
    }
    public void nall()
    {
        for (int i = 0 ;i<ch.size();i++)
        {
            ch.set(i,false);
        }
        notifyDataSetChanged();
    }
    public List<Boolean> getCh()
    {
        return ch;
    }
}
