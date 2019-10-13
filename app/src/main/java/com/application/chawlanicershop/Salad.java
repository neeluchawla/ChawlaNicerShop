package com.application.chawlanicershop;


import android.util.Log;

import java.text.DecimalFormat;

/*

Model class
 */
public class Salad {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private final int thumbnail;
    private String description;
    private String subtotal;
    private int addQuantity;
    private int subQuantity;
    private boolean veg=false,gluten_free=false;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private static final String LOG_TAG =
            Salad.class.getSimpleName();
    public Salad(int thumbnail){

        this.thumbnail = thumbnail;
    }

    public Salad(int id, String title, String description, double price, int thumbnail,boolean veg,boolean gluten_free) {
        Log.d(LOG_TAG,"constructor to set value");
        this.title = title;
        this.price = price;
        this.thumbnail=thumbnail;
        this.description=description;
        this.id=id;
        this.veg=veg;
        this.gluten_free=gluten_free;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubtotal() {
        return df2.format(price*quantity);
    }

    public void setSubtotal(String subtotal) {

        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addToQuantity(){

        Log.d(LOG_TAG,"add quantity");
        this.quantity += 1;
    }

    public void subQuantity(){
        if(this.quantity >= 1){
            Log.d(LOG_TAG,"reduce quantity");
            this.quantity -= 1;
        }
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
    }
    public Boolean getGluten_free() {
        return gluten_free;
    }

    public void setGluten_free(Boolean gluten_free) {
        this.gluten_free = gluten_free;
    }

}
