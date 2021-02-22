package com.example.spring_session.service;

import com.example.spring_session.dto.ProductRequestDTO;
import com.example.spring_session.dto.SearchResponseDTO;

public interface SearchService {
    public SearchResponseDTO getProducts(ProductRequestDTO request);
}
