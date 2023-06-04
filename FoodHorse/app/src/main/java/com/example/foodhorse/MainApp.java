package com.example.foodhorse;

import android.app.Application;

public class MainApp extends Application {

    private int type =0;
    private Entuty_User entuty_user;
    private Entuty_Commodity entuty_commodity;
    private Entuty_Shop entuty_shop;

    public int getType(){return type;}
    public Entuty_User getEntuty_user(){return entuty_user;}
    public Entuty_Commodity getEntuty_commodity(){return entuty_commodity;}
    public Entuty_Shop getEntuty_shop() {return entuty_shop;}

    public void setType(int type){this.type=type;}
    public void setEntuty_user(Entuty_User entuty_user){this.entuty_user = entuty_user;}
    public void setEntuty_commodity(Entuty_Commodity entuty_commodity){this.entuty_commodity = entuty_commodity;}
    public void setEntuty_shop(Entuty_Shop entuty_shop) {this.entuty_shop = entuty_shop;}
    public void clear()
    {
        this.type = 0;
        this.entuty_user = null;
        this.entuty_commodity = null;
        this.entuty_shop = null;
    }

}
