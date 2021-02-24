package com.example.spring_session.service.impl;

import com.example.spring_session.client.SearchClient;
import com.example.spring_session.dto.ProductRequestDTO;
import com.example.spring_session.dto.ProductResponseDTO;
import com.example.spring_session.dto.SearchResponseDTO;
import com.example.spring_session.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchClient searchClient;

    @Override
    public SearchResponseDTO getProducts(ProductRequestDTO request) {
        SearchResponseDTO responseDTO = new SearchResponseDTO();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable productTask = () -> {

            System.out.println("Task 1 : " + Thread.currentThread().getName());
            ProductResponseDTO myProductDetails[] = new ProductResponseDTO[10];
            Map<String, Object> productResponse = searchClient.getProducts(request.getSearchTerm());
            Map<String, Object> response = (Map<String,Object>) productResponse.get("response");
            List<Map<String, Object>> products = (List<Map<String,Object>>) (response.get("docs"));

            for(int i=0; i<10; i++) {
                myProductDetails[i] = new ProductResponseDTO();
                int stock = (int)products.get(i).get("isInStock");
                if(stock==1)
                    myProductDetails[i].setInStock(true);
                else
                    myProductDetails[i].setInStock(false);
                myProductDetails[i].setTitle((String) products.get(i).get("name"));
//                myProductDetails[i].setSalePrice((int)((double) products.get(i).get("salePrice")));
                myProductDetails[i].setDescription((String) products.get(i).get("description"));
                myProductDetails[i].setLocation((List<String>)(products.get(i).get("stockLocation")));
            }
            responseDTO.setProducts(Arrays.asList(myProductDetails));
        };

        Runnable locationTask = () -> {
            System.out.println("Task 2 : " + Thread.currentThread().getName());

            ProductResponseDTO myProductDetailsLoc[] = new ProductResponseDTO[10];

            Map<String, Object> productResponseLoc = searchClient.getProducts("stockLocation:" + request.getStockLocation());
            Map<String, Object> responseLoc = (Map<String,Object>) productResponseLoc.get("response");
            List<Map<String, Object>> productsLoc = (List<Map<String,Object>>) (responseLoc.get("docs"));

            for(int i=0; i<10; i++) {
                myProductDetailsLoc[i] = new ProductResponseDTO();
                int stock = (int)productsLoc.get(i).get("isInStock");
                if(stock==1)
                    myProductDetailsLoc[i].setInStock(true);
                else
                    myProductDetailsLoc[i].setInStock(false);
                myProductDetailsLoc[i].setTitle((String) productsLoc.get(i).get("name"));
//                myProductDetailsLoc[i].setSalePrice((int)((double) productsLoc.get(i).get("salePrice")));
                myProductDetailsLoc[i].setDescription((String) productsLoc.get(i).get("description"));
                myProductDetailsLoc[i].setLocation((List<String>)(productsLoc.get(i).get("stockLocation")));
            }
            responseDTO.setLocationBasedProducts(Arrays.asList(myProductDetailsLoc));
        };

        executor.execute(productTask);
        executor.execute(locationTask);

        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        return responseDTO;
    }

    /*@Override
    public SearchResponseDTO getProducts(ProductRequestDTO request) {

        String searchTermQuery = request.getSearchTerm();
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        String locationQuery = "stockLocation:\""   + request.getStockLocation() + "\"";

        List<ProductResponseDTO> productDTOS = getProducts(searchTermQuery);
        List<ProductResponseDTO> locationProductDTOs = getProducts(locationQuery);

        responseDTO.setProducts(productDTOS);
        responseDTO.setLocationBasedProducts(locationProductDTOs);
        return responseDTO;
    }

    private List<ProductDTO> getSearchTermBaseProducts(String query) {
        Map<String, Object> productResponse = searchClient.getProducts(query);
        Map<String, Object> responseObject = (Map<String, Object>) (productResponse.get("response"));
        List<Map<String, Object>> products = (List<Map<String, Object>>) responseObject.get("docs");
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Map<String, Object> productClientResponse :products) {
            String title = (String) productClientResponse.get(SolrFieldNames.NAME);
            boolean inStock = (int) productClientResponse.get(SolrFieldNames.IN_STOCK) == 1? true: false;
            String description = (String) productClientResponse.get(SolrFieldNames.DESCRIPTION);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setInsStock(inStock);
            productDTO.setTitle(title);
            productDTO.setDescription(description);

            productDTOS.add(productDTO);
        }
        return productDTOS;
    }*/
}