package com.example.foodhorse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fmgr;
    private FragmentTransaction fragmentTransaction;
    private ViewGroup container;
    private Fragment Home,Search,Shop,User,ShopUser,DriverUser,Maps,DriverShopOrderInfomation,UserOrder;
    MainApp mainApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        container = (ViewGroup) findViewById(R.id.container);
        fmgr = getSupportFragmentManager();
        Home = new HomeFragment();
        Search = new SearchFragment();
        Shop = new ShopFragment();
        User = new UserFragment();
        ShopUser = new ShopUserFragment();
        DriverUser = new DriverUserFragment();
        Maps = new MapsFragment();
        UserOrder = new User_Order_Fragment();
        DriverShopOrderInfomation = new Driver_Shop_Order_Information_Fragment();
        fragmentTransaction = fmgr.beginTransaction();
        fragmentTransaction.add(R.id.container,Home);
        fragmentTransaction.commit();
        mainApp = (MainApp) getApplication();
    }


    public void OpenDriverOrder()
    {
        fragmentTransaction = fmgr.beginTransaction();
        fragmentTransaction.replace(R.id.container,DriverShopOrderInfomation);
        fragmentTransaction.commit();
    }
    public void Home_Button(View view)
    {
        fragmentTransaction = fmgr.beginTransaction();
        fragmentTransaction.replace(R.id.container,Home);
        fragmentTransaction.commit();
    }
    public void Search_Button(View view)
    {
        fragmentTransaction = fmgr.beginTransaction();
        fragmentTransaction.replace(R.id.container,Search);
        fragmentTransaction.commit();
    }
    public void Shop_Button(View view)
    {
        if (mainApp.getType()==3)
        {
            String[] field = new String[1];
            field[0] = "hID";
            String[] data = new String[1];
            data[0] = Integer.toString(mainApp.getEntuty_user().getID());
            //使用者名稱 使用者電話 店家名稱 店家電話 店家住址 使用者地址 訂單備註 訂單總價格
            String result = use.getResult("http://20.187.122.219/horse/orders/deliver.php", field, data);
            if (result.equals("No Order"))
            {
                fragmentTransaction = fmgr.beginTransaction();
                fragmentTransaction.replace(R.id.container, Maps);
                fragmentTransaction.commit();
            }
            else
            {
                OpenDriverOrder();
            }
        }
        else if(mainApp.getType()==1)
        {
            fragmentTransaction = fmgr.beginTransaction();
            fragmentTransaction.replace(R.id.container, UserOrder);
            fragmentTransaction.commit();
        }
        else if(mainApp.getType()!=0)
        {
            fragmentTransaction = fmgr.beginTransaction();
            fragmentTransaction.replace(R.id.container, Shop);
            fragmentTransaction.commit();
        }
        else
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
    public void User_Button(View view)
    {
        if(mainApp.getType()==1)
        {

            fragmentTransaction = fmgr.beginTransaction();
            fragmentTransaction.replace(R.id.container, User);
            fragmentTransaction.commit();
        }
        else if(mainApp.getType()==3)
        {
            fragmentTransaction = fmgr.beginTransaction();
            fragmentTransaction.replace(R.id.container, DriverUser);
            fragmentTransaction.commit();
        }
        else if(mainApp.getType()==2)
        {
            fragmentTransaction = fmgr.beginTransaction();
            fragmentTransaction.replace(R.id.container, ShopUser);
            fragmentTransaction.commit();
        }
        else if(mainApp.getType()==0)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}