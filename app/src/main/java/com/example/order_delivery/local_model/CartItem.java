package com.example.order_delivery.local_model;


import com.parse.Parse;
import com.parse.ParseFile;

import java.io.File;

public class CartItem {
    private int quantity;
    private String name;
    private String imageUrl;
    private double price;

    public CartItem(){
        quantity = 0;
        name = null;
        imageUrl = null;
        price = 0;
    }

    public void setField(String name, int quantity, String imageUrl, double price){
        this.name = name;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getName(){
        return this.name;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
    public double getPrice(){
        return this.price;
    }
}
