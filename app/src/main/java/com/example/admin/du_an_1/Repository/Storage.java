package com.example.admin.du_an_1.Repository;

public class Storage {
    private String id;
    private String productName;
    private String productCode;
    private int quantity;
    private String date;

    public Storage() {
    }

    public Storage(String id, String productName, String productCode, int quantity, String date) {
        this.id = id;
        this.productName = productName;
        this.productCode = productCode;
        this.quantity = quantity;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
