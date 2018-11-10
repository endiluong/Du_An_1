package com.example.admin.du_an_1.Repository;

import java.io.Serializable;

public class Ticket implements Serializable {
    private String id;
    private String tittle;
    private String date;
    private Boolean type;
    private String productName;
    private String productCode;
    private int quantity;

    public Ticket() {
    }

    public Ticket(String id, String tittle, String date, Boolean type, String productName, String productCode, int quantity) {
        this.id = id;
        this.tittle = tittle;
        this.date = date;
        this.type = type;
        this.productName = productName;
        this.productCode = productCode;
        this.quantity = quantity;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
