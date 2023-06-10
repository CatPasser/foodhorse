package com.example.foodhorse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class listlayoutadapter extends BaseAdapter {

    private List<Entuty_Commodity> commodityLiset;
    private LayoutInflater mLayInf;
    private String url;

    public listlayoutadapter(Context context,List<Entuty_Commodity> commodityLiset)
    {
        mLayInf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.commodityLiset = commodityLiset;
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
        View v = mLayInf.inflate(R.layout.listlayout,viewGroup,false);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);
        TextView textView = (TextView) v.findViewById(R.id.text);
        TextView textView2 = (TextView) v.findViewById(R.id.tag);
        TextView textView3 = (TextView) v.findViewById(R.id.price);
        TextView textView4 = (TextView) v.findViewById(R.id.time);
        TextView textView5 = (TextView) v.findViewById(R.id.updata);

        //imageView.setImageResource((mItemList2.get(i)));
        textView.setText(commodityLiset.get(i).getCommodity_name());
        textView2.setText(commodityLiset.get(i).getCommodity_tag());
        textView3.setText("價格：$ "+commodityLiset.get(i).getCommodity_price());
        textView4.setText("上架時間：\n"+commodityLiset.get(i).getTime());
        textView5.setText("更新時間：\n"+commodityLiset.get(i).getUp());

        url = commodityLiset.get(i).getCommodity_photo();
        new use.ImageLoad(imageView,url).execute();

        return v;
    }

    public Entuty_Commodity getCommodity(int i)
    {return commodityLiset.get(i);}

}