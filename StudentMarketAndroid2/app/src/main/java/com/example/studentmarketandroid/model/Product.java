package com.example.studentmarketandroid.model;
import com.google.gson.annotations.SerializedName;
public class Product {
    public int id;

    @SerializedName("seller_id")
    public int sellerId;

    @SerializedName("category_id")
    public int categoryId;

    public String name;
    public String description;
    public double price;

    @SerializedName("product_condition")
    public String productCondition;

    public String status;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("updated_at")
    public String updatedAt;
}

