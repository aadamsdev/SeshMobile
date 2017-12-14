package com.aadamsdev.seshmobile;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by andrewadams on 2017-11-08.
 */

public class Product implements Parcelable{

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

    public Product(Parcel in) {
        String[] stringArgs = new String[3];
        float[] floatArgs = new float[1];
        boolean[] booleanArgs = new boolean[1];

        in.readStringArray(stringArgs);
        in.readFloatArray(floatArgs);
        in.readBooleanArray(booleanArgs);

        this.productName = stringArgs[0];
        this.productUrl = stringArgs[1];
        this.imageUrl = stringArgs[2];

        this.price = floatArgs[0];

        this.soldOut = booleanArgs[0];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String[] stringArgs = {this.productName, this.productUrl, this.imageUrl};
        float[] floatArgs = {this.price};
        boolean[] booleanArgs = {this.soldOut};

        dest.writeStringArray(stringArgs);
        dest.writeFloatArray(floatArgs);
        dest.writeBooleanArray(booleanArgs);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
