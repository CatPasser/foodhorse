package com.example.foodhorse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class shopping_checkout_listadarter extends BaseAdapter{

    private List<Entuty_Commodity> commodityLiset;
    private LayoutInflater mLayInf;
    private String url;
    private List<String> amount;

    public shopping_checkout_listadarter(Context context, List<Entuty_Commodity> commodityLiset,List<String> amount)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.commodityLiset = commodityLiset;
        this.amount = amount;
    }

    @Override
    public int getCount() {
        return commodityLiset.size();
    }

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
        View v = mLayInf.inflate(R.layout.shopping_checkout_list,viewGroup,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView price = (TextView) v.findViewById(R.id.price);
        TextView count = (TextView) v.findViewById(R.id.count);
        TextView notes = (TextView) v.findViewById(R.id.notes);

        count.setText("數量:"+amount.get(i));
        name.setText(commodityLiset.get(i).getCommodity_name());
        price.setText("$"+commodityLiset.get(i).getCommodity_price());
        notes.setText("備註:\n"+commodityLiset.get(i).getCommodity_notes());

        url = commodityLiset.get(i).getCommodity_photo();
        new use.ImageLoad(imageView,url).execute();

        return v;
    }
}
