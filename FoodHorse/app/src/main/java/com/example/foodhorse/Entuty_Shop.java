package com.example.foodhorse;

public class Entuty_Shop {
    private int ID;
    private String name;
    private  String tag;
    private String phone;
    private String address;
    private String photo;
    private String evaluation;
    Entuty_Shop(int ID,String name,String tag ,String phone, String address, String photo, String evaluation)
    {
        this.ID = ID;
        this.name = name;
        this.tag = tag;
        this.phone = phone;
        this.address = address;
        this.photo = "http://20.187.122.219/"+photo;
        this.evaluation = evaluation;
    }

    public int getID() {return ID;}
    public String getName(){return name;}
    public String getTag() {return tag;}
    public String getPhone() {return phone;}
    public String getAddress() {return address;}
    public String getPhoto() {return photo;}
    public String getEvaluation() {return evaluation;}

    public void setID(int ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setTag(String tag) {this.tag = tag;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setAddress(String address) {this.address = address;}
    public void setPhoto(String photo) {this.photo = "http://20.187.122.219/"+photo;}
    public void setEvaluation(String evaluation) {this.evaluation = evaluation;}
}
