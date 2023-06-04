package com.example.foodhorse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_Order_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_Order_Fragment extends Fragment {

    private ListView listView;
    MainApp mainApp;
    List<String> oID ;
    List<String> shopname;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User_Order_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_Order_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static User_Order_Fragment newInstance(String param1, String param2) {
        User_Order_Fragment fragment = new User_Order_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_user__order_, container, false);
        mainApp = (MainApp) getActivity().getApplication();
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Activity MyActivity = getActivity();
                Intent intent = new Intent(MyActivity,User_Order_Info.class);
                intent.putExtra("oID",oID.get(i));
                intent.putExtra("shopname",shopname.get(i));
                startActivity(intent);
            }
        });
        String[] field = new String[1];
        field[0] = "uID";
        String[] data = new String[1];
        data[0] = Integer.toString(mainApp.getEntuty_user().getID());
        String result = use.getResult("http://20.187.122.219/users/orders/getOrder.php", field, data);
        if (!result.equals("No Order")){
            String array[] = result.split("&&");//oID 店家名稱
            oID = new ArrayList<>();
            shopname = new ArrayList<>();
            for (int i2 = 0; i2 < array.length; i2++) {
                String array2[] = array[i2].split(",");
                oID.add(array2[0]);
                shopname.add(array2[1]);
            }
            listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,shopname));
        }
        return view;
    }

}