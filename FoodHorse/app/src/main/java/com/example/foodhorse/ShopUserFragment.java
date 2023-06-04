package com.example.foodhorse;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopUserFragment extends Fragment {

    private MainApp mainApp;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopUserFragment newInstance(String param1, String param2) {
        ShopUserFragment fragment = new ShopUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_shop_user, container, false);
        mainApp = (MainApp) getActivity().getApplication();

        Button button = view.findViewById(R.id.signout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity MyActivity = getActivity();

                ((MainActivity) MyActivity).mainApp.clear();

                AlertDialog.Builder builder = new AlertDialog.Builder((MyActivity));
                builder.setTitle("Nice");
                builder.setMessage("登出成功");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((MainActivity) MyActivity).findViewById(R.id.HomeButton).performClick();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        Button button2 = view.findViewById(R.id.info);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity MyActivity = getActivity();
                Intent intent = new Intent(MyActivity,ShopInfo.class);
                startActivity(intent);
            }
        });
        Button button3 = view.findViewById(R.id.commodity);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity MyActivity = getActivity();
                Intent intent = new Intent(MyActivity,Commodity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}