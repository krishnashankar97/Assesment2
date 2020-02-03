package com.northwind.shipping.domain;

public class PackingSlipDetails {
     private long id;
     private String productName;
     private int Quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null ) {
            throw new IllegalArgumentException("ProductName is required.");
        }
        String cleanName = productName.trim();
        if (cleanName.length() == 0 || cleanName.length() > 60) {
            throw new IllegalArgumentException("ProductName must be between 1 and 60 characters.");
        }
        this.productName = cleanName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {

        if(quantity == 0 || quantity > 30)
            throw new IllegalArgumentException("Quantity must be between 1 and 30.");
        this.Quantity = quantity;
    }
}
