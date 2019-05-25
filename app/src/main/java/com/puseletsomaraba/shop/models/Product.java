package com.puseletsomaraba.shop.models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private String price;
    private String taxAmount;
    private String importAmount;
    private String urlImage;
    private boolean imported;
    private int cartItem;
    private int categoryId;

    public Product()
    {
        this.name = "";
        this.price = "";
        this.taxAmount = "";
        this.importAmount = "";
        this.urlImage = "";
        this.imported = false;

    }

    //getter....................................

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public String getImportAmount() {
        return importAmount;
    }
    public String getImgURL() {
        return urlImage;
    }
    public Boolean getImported() {
        return imported;
    }

    public int getId() {
        return id;
    }

//setter.............................

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setImportAmount(String importAmount) {
        this.importAmount = importAmount;
    }

    public void setImgURL(String imgURL) {
        this.urlImage = imgURL;
    }


    public int getCartItem() {
        return cartItem;
    }

    public void setCartItem(int cartItem) {
        this.cartItem = this.cartItem + cartItem;
    }

    public boolean isTaxApllicable(){

        switch (this.categoryId){

            case 4:
                return true;
            case 5:
                return true;

        }

        return false;
    }
}
