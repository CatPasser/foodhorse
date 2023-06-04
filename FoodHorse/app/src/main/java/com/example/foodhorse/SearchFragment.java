package com.example.foodhorse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private listlayoutadapter2 mListAdapter;
    private EditText search_text;
    private ListView list_view;
    MainApp mainApp;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        list_view = (ListView)view.findViewById(R.id.list_view);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Activity MyActivity = getActivity();
                ((MainActivity) MyActivity).mainApp.setEntuty_shop(mListAdapter.getShop(i));
                Intent intent = new Intent(getContext(),Browse_Shop.class);
                startActivity(intent);
                //開啟店家頁面
            }
        });
        search_text = (EditText)view.findViewById(R.id.search_text);
        search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String[] field = new String[1];
                field[0] = "text";
                String[] data = new String[1];
                data[0] = search_text.getText().toString().replace("\n","");
                search_text.setText("");
                String result = use.getResult("http://20.187.122.219/home/search/search.php",field,data);
                if (!result.equals("No query result")){
                    String array[] = result.split("&&");//name tag photo
                    List<Entuty_Shop> shops= new ArrayList<>();
                    for (int i2 = 0; i2 < array.length; i2++) {
                        String array2[] = array[i2].split(",");
                        shops.add(new Entuty_Shop(Integer.parseInt(array2[0]),array2[1],array2[2],null,null,array2[3],null));
                    }
                    mListAdapter = new listlayoutadapter2(view.getContext(),shops);
                    list_view.setAdapter(mListAdapter);
                }
                return false;
            }
        });


        Button button = view.findViewById(R.id.car);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainApp.getType()==0)
                {
                    Activity MyActivity = getActivity();
                    Intent intent = new Intent(MyActivity,LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getContext(), Shopping_Car.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}