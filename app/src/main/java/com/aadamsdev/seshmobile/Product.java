package com.aadamsdev.seshmobile;

import java.io.Serializable;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class Product implements Serializable{

    private String productName, productUrl, imageUrl;
    private float price;
    private boolean soldOut;

    public Product(String productName, String productUrl, String imageUrl, float price, boolean soldOut) {
        this.productName = productName;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
        this.price = price;
        this.soldOut = soldOut;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
