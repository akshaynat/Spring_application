package com.example.spring_session.dto;

public class ProductResponseDTO {
    private boolean inStock;
    private int salePrice;
    private String description;
    private String title;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "inStock=" + inStock +
                ", salePrice=" + salePrice +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
