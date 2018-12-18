package com.example.admin.du_an_1.Repository;

public class Product {
    private String id;
    private String name;
    private String code;
    private int quantity;
    private String category;

    public Product() {
    }

    public Product(String id, String name, String code, int quantity ,String category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.quantity= quantity;
        this.category=category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
