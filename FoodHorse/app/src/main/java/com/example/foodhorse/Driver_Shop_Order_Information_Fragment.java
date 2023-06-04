package com.example.foodhorse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Driver_Shop_Order_Information_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Driver_Shop_Order_Information_Fragment extends Fragment {

    ScrollView Sview;
    LinearLayout linearLayout;
    MainApp mainApp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Driver_Shop_Order_Information_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Driver_Shop_Order_Information_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Driver_Shop_Order_Information_Fragment newInstance(String param1, String param2) {
        Driver_Shop_Order_Information_Fragment fragment = new Driver_Shop_Order_Information_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainApp = (MainApp) getActivity().getApplication();
        View view = inflater.inflate(R.layout.fragment_driver__shop__order__information_, container, false);

        Sview = (ScrollView) view.findViewById(R.id.view);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout);
        TextView shopname = new TextView(getContext());
        shopname.setTextColor(Color.BLACK);
        shopname.setTextSize(30);
        shopname.setGravity(Gravity.CENTER);;

        TextView shopphone = new TextView(getContext());
        shopphone.setTextColor(Color.BLACK);
        shopphone.setTextSize(20);

        TextView shopaddress = new TextView(getContext());
        shopaddress.setTextColor(Color.BLACK);
        shopaddress.setTextSize(20);

        TextView username = new TextView(getContext());
        username.setTextColor(Color.BLACK);
        username.setTextSize(20);

        TextView userphone = new TextView(getContext());
        userphone.setTextColor(Color.BLACK);
        userphone.setTextSize(20);

        TextView useraddress = new TextView(getContext());
        useraddress.setTextColor(Color.BLACK);
        useraddress.setTextSize(20);

        TextView ordernotes = new TextView(getContext());
        ordernotes.setTextColor(Color.BLACK);
        ordernotes.setTextSize(20);

        TextView orderprice = new TextView(getContext());
        orderprice.setTextColor(Color.BLACK);
        orderprice.setTextSize(20);
        orderprice.setGravity(Gravity.RIGHT);

        TextView orderinfo = new TextView(getContext());
        orderinfo.setTextColor(Color.BLACK);
        orderinfo.setTextSize(20);
        orderinfo.setBackgroundColor(Color.rgb(204,209,202));

        Button EndOrder = new Button(getContext());
        EndOrder.setText("送達訂單");
        EndOrder.setGravity(Gravity.CENTER);
        EndOrder.setWidth(25);
        EndOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] field = new String[1];
                field[0] = "hID";
                String[] data = new String[1];
                data[0] = Integer.toString(mainApp.getEntuty_user().getID());
                String result = use.getResult("http://20.187.122.219/horse/orders/arrived.php", field, data);
                EndOrder.setVisibility(View.GONE);
            }
        });



        String[] field = new String[1];
        field[0] = "hID";
        String[] data = new String[1];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        //使用者名稱 使用者電話 店家名稱 店家電話 店家住址 使用者地址 訂單備註 訂單總價格
        String result = use.getResult("http://20.187.122.219/horse/orders/deliver.php", field, data);
        if(!result.equals("No Order"))
        {
            String array[] = result.split("&&");//資訊 訂單
            String array2[] = array[0].split(",");
            String array3[] = array[1].split(",");
            username.setText("用戶名稱:"+array2[0]);
            userphone.setText("用戶電話:"+array2[1]);
            shopname.setText(array2[2]);
            shopphone.setText("店家電話:"+array2[3]);
            shopaddress.setText("店家地址:"+array2[4]);
            useraddress.setText("送達地址:"+array2[5]);
            ordernotes.setText("備註:"+array2[6]);
            orderprice.setText("總金額:"+array2[7]);
            String info = "";
            for(int i = 0 ; i < array3.length;i++)
            {
                info+=array3[i]+"\n";
            }
            orderinfo.setText(info);
        }
        linearLayout.addView(shopname);
        linearLayout.addView(shopphone);
        linearLayout.addView(shopaddress);
        linearLayout.addView(username);
        linearLayout.addView(userphone);
        linearLayout.addView(useraddress);
        linearLayout.addView(orderinfo);
        linearLayout.addView(orderprice);
        linearLayout.addView(ordernotes);
        linearLayout.addView(EndOrder);

        return view;
    }
}