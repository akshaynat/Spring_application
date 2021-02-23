package com.example.spring_session.dto;

import java.util.List;

public class SearchResponseDTO {

    private List<ProductResponseDTO> products;
    private List<ProductResponseDTO> locationBasedProducts;

    public List<ProductResponseDTO> getLocationBasedProducts() {
        return locationBasedProducts;
    }

    public void setLocationBasedProducts(List<ProductResponseDTO> locationBasedProducts) {
        this.locationBasedProducts = locationBasedProducts;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }
}
