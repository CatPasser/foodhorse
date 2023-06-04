package com.example.foodhorse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listlayoutadapter2 extends BaseAdapter {

    private List<Entuty_Shop> shopList;
    private LayoutInflater mLayInf;
    private String url;

    public listlayoutadapter2(Context context,List<Entuty_Shop> entuty_shops)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        shopList = entuty_shops;
    }
    @Override
    public int getCount() {
        return shopList.size();
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
        View v = mLayInf.inflate(R.layout.listlayout2,viewGroup,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        TextView textView = (TextView) v.findViewById(R.id.text);
        TextView textView2 = (TextView) v.findViewById(R.id.tag);

        //imageView.setImageResource((mItemList2.get(i)));
        textView.setText(shopList.get(i).getName());
        textView2.setText(shopList.get(i).getTag());

        url = shopList.get(i).getPhoto();
        new use.ImageLoad(imageView,url).execute();

        return v;
    }

    public Entuty_Shop getShop(int i)
    {return shopList.get(i);}

}