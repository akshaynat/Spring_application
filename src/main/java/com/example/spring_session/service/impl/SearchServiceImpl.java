package com.example.spring_session.service.impl;

import com.example.spring_session.client.SearchClient;
import com.example.spring_session.dto.ProductRequestDTO;
import com.example.spring_session.dto.ProductResponseDTO;
import com.example.spring_session.dto.SearchResponseDTO;
import com.example.spring_session.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchClient searchClient;

    @Override
    public SearchResponseDTO getProducts(ProductRequestDTO request) {

        ProductResponseDTO myProductDetails[] = new ProductResponseDTO[10];
        ProductResponseDTO myProductDetailsLoc[] = new ProductResponseDTO[10];

        Map<String, Object> productResponse = searchClient.getProducts(request.getSearchTerm());
        Map<String, Object> response = (Map<String,Object>) productResponse.get("response");
        List<Map<String, Object>> products = (List<Map<String,Object>>) (response.get("docs"));

        Map<String, Object> productResponseLoc = searchClient.getProducts("stockLocation:" + request.getStockLocation());
        Map<String, Object> responseLoc = (Map<String,Object>) productResponse.get("response");
        List<Map<String, Object>> productsLoc = (List<Map<String,Object>>) (response.get("docs"));

        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ProductResponseDTO[] product = new ProductResponseDTO[2];

        for(int i=0; i<10; i++) {
            myProductDetails[i] = new ProductResponseDTO();
            int stock = (int)products.get(i).get("isInStock");
            if(stock==1)
                myProductDetails[i].setInStock(true);
            else
                myProductDetails[i].setInStock(false);
            myProductDetails[i].setTitle((String) products.get(i).get("name"));
            myProductDetails[i].setSalePrice((int)((double) products.get(i).get("salePrice")));
            myProductDetails[i].setDescription((String) products.get(i).get("description"));
        }

        for(int i=0; i<10; i++) {
            myProductDetailsLoc[i] = new ProductResponseDTO();
            int stock = (int)productsLoc.get(i).get("isInStock");
            if(stock==1)
                myProductDetailsLoc[i].setInStock(true);
            else
                myProductDetailsLoc[i].setInStock(false);
            myProductDetailsLoc[i].setTitle((String) productsLoc.get(i).get("name"));
            myProductDetailsLoc[i].setSalePrice((int)((double) productsLoc.get(i).get("salePrice")));
            myProductDetailsLoc[i].setDescription((String) productsLoc.get(i).get("description"));
        }

        responseDTO.setProducts(Arrays.asList(myProductDetails));
        responseDTO.setLocationBasedProducts(Arrays.asList(myProductDetailsLoc));
        return responseDTO;
    }
}