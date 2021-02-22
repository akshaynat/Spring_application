package com.example.spring_session.service.impl;

import com.example.spring_session.dto.ProductRequestDTO;
import com.example.spring_session.dto.ProductResponseDTO;
import com.example.spring_session.dto.SearchResponseDTO;
import com.example.spring_session.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SearchServiceList implements SearchService {

    @Override
    public SearchResponseDTO getProducts(ProductRequestDTO request) {
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ProductResponseDTO[] product = new ProductResponseDTO[2];
        for(int i=1; i<3; i++) {
            product[i] = new ProductResponseDTO();
            product[i].setDescription("Samsung galaxy s"+i+" blah blah..");
            product[i].setInStock(true);
            product[i].setSalePrice(10000*i);
            product[i].setTitle("Samsung galaxy s" + i);
        }
        responseDTO.setProducts(Arrays.asList(product));
        return responseDTO;
    }
}
