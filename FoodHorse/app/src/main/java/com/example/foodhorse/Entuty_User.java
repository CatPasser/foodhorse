package com.example.foodhorse;

public class Entuty_User {
    private int ID;
    private String account;
    private String name;
    private String phone;
    private String address;
    private String photo;
    private String tag;

    Entuty_User(int ID,String account,String name,String phone,String address,String photo,String tag)
    {
        this.ID = ID;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.photo = "http://20.187.122.219/"+photo;
        this.tag = tag;
    }

    public int getID(){return ID;}
    public String getAccount(){return account;}
    public String getName(){return name;}
    public String getPhone(){return phone;}
    public String getAddress(){return address;}
    public String getPhoto(){return photo;}
    public String getTag(){return tag;}
    public void setID(int ID){this.ID=ID;}
    public void setAccount(String account){this.account = account;}
    public void setName(String name){this.name =name;}
    public void setPhone(String phone){this.phone=phone;}
    public void setAddress(String address){this.address=address;}
    public void setPhoto(String photo){this.photo = "http://20.187.122.219/"+photo;}
    public void setTag(String tag){this.tag=tag;}
}
