package com.example.spring_session.dto;

import java.util.List;

public class SearchResponseDTO {

    private List<ProductResponseDTO> products;

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }
}
