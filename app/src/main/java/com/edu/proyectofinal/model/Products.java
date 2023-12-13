package com.edu.proyectofinal.model;

import com.google.gson.annotations.SerializedName;

public class Products  {
    @SerializedName("product_id")
    private int product_id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("product_unit")
    private int product_unit;
    @SerializedName("product_value")
    private int product_value;
    @SerializedName("product_image")
    private String product_image;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_unit() {
        return product_unit;
    }

    public void setProduct_unit(int product_unit) {
        this.product_unit = product_unit;
    }

    public int getProduct_value() {
        return product_value;
    }

    public void setProduct_value(int product_value) {
        this.product_value = product_value;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
