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

    public shopping_car_listadarter(Context context, List<Entuty_Commodity> commodityLiset ,Context context2,List<String> amount)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.commodityLiset = commodityLiset;
        this.context = context2;
        this.amount = amount;
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


        name.setText(commodityLiset.get(i).getCommodity_name());
        price.setText("$"+commodityLiset.get(i).getCommodity_price());

        CompoundButton.OnCheckedChangeListener ck = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                notifyDataSetChanged();
            }
        };

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
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //刪除餐點
                            count.setText(Integer.toString(countNumber));
                            commodityLiset.remove(i);
                            amount.remove(i);
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
}
