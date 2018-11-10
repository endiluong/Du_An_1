package com.example.admin.du_an_1.Repository;

public class Storage {
    private String id;
    private String productName;
    private String productCode;
    private int number;
    private String date;

    public Storage() {
    }

    public Storage(String id, String productName, String productCode, int number, String date) {
        this.id = id;
        this.productName = productName;
        this.productCode = productCode;
        this.number = number;
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


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
