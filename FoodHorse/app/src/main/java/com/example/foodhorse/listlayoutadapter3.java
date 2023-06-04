package com.example.foodhorse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listlayoutadapter3 extends BaseAdapter {

    private List<Entuty_Commodity> commodityList;
    private LayoutInflater mLayInf;
    private String url;

    public listlayoutadapter3(Context context,List<Entuty_Commodity> commodityList)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.commodityList = commodityList;
    }
    @Override
    public int getCount() {
        return commodityList.size();
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
        View v = mLayInf.inflate(R.layout.listlayout,viewGroup,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        TextView textView = (TextView) v.findViewById(R.id.text);
        TextView textView2 = (TextView) v.findViewById(R.id.tag);
        TextView textView3 = (TextView) v.findViewById(R.id.price);

        //imageView.setImageResource((mItemList2.get(i)));
        textView.setText(commodityList.get(i).getCommodity_name());
        textView2.setText(commodityList.get(i).getCommodity_tag());
        textView3.setText("價格：$ "+commodityList.get(i).getCommodity_price());

        url = commodityList.get(i).getCommodity_photo();
        new use.ImageLoad(imageView,url).execute();

        return v;
    }

    public Entuty_Commodity getCommodity(int i)
    {return commodityList.get(i);}


}