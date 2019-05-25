package com.puseletsomaraba.shop.models;

import java.io.Serializable;

public class CartProduct implements Serializable {

    private int quantity;
    private Product product;



    public CartProduct(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

}
