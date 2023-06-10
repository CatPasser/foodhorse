package com.example.foodhorse;

public class Entuty_Commodity {
    private int ID;
    private String Commodity_name;
    private String Commodity_tag;
    private String Commodity_price;
    private String Commodity_notes;
    private String Commodity_photo;
    private String time;
    private String up;
    Entuty_Commodity(String Commodity_name,String Commodity_tag,String Commodity_price,String Commodity_notes,String Commodity_photo)
    {
        this.Commodity_name = Commodity_name;
        this.Commodity_tag = Commodity_tag;
        this.Commodity_price = Commodity_price;
        this.Commodity_notes = Commodity_notes;
        this.Commodity_photo = "http://20.187.122.219/"+Commodity_photo;
        this.time = null;
        this.up = null;
    }
    Entuty_Commodity(String Commodity_name,String Commodity_tag,String Commodity_price,String Commodity_notes,String Commodity_photo,int ID)
    {
        this.Commodity_name = Commodity_name;
        this.Commodity_tag = Commodity_tag;
        this.Commodity_price = Commodity_price;
        this.Commodity_notes = Commodity_notes;
        this.Commodity_photo = "http://20.187.122.219/"+Commodity_photo;
        this.time = null;
        this.up = null;
        this.ID = ID;
    }
    Entuty_Commodity(String Commodity_name,String Commodity_tag,String Commodity_price,String Commodity_notes,String Commodity_photo,String time,String up)
    {
        this.Commodity_name = Commodity_name;
        this.Commodity_tag = Commodity_tag;
        this.Commodity_price = Commodity_price;
        this.Commodity_notes = Commodity_notes;
        this.Commodity_photo = "http://20.187.122.219/"+Commodity_photo;
        this.time = time;
        this.up = up;
    }
    public String getCommodity_name() {return Commodity_name;}
    public String getCommodity_tag() {return Commodity_tag;}
    public String getCommodity_price() {return Commodity_price;}
    public String getCommodity_notes() {return Commodity_notes;}
    public String getCommodity_photo() {return Commodity_photo;}
    public String getTime() {return time;}
    public String getUp() {return up;}
    public int getID() {return ID;}

    public void setCommodity_name(String commodity_name) {Commodity_name = commodity_name;}
    public void setCommodity_price(String commodity_price) {Commodity_price = commodity_price;}
    public void setCommodity_tag(String commodity_tag) {Commodity_tag = commodity_tag;}
    public void setCommodity_notes(String commodity_notes) {Commodity_notes = commodity_notes;}
    public void setCommodity_photo(String commodity_photo) {Commodity_photo = "http://20.187.122.219/"+commodity_photo;}
    public void setTime(String time) {this.time = time;}
    public void setUp(String up) {this.up = up;}
    public void setID(int ID) {this.ID = ID;}
}
