package com.example.spring_session.service.impl;

import com.example.spring_session.client.SearchClient;
import com.example.spring_session.dto.ProductRequestDTO;
import com.example.spring_session.dto.ProductResponseDTO;
import com.example.spring_session.dto.SearchResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProducts() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resource/search.mock"), Map.class);

        Map<String, Object> locationMockObject = objectMapper.readValue(new URL ("file:src/test/resource/location.mock"), Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:Jakarta")).thenReturn(locationMockObject);


        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("Jakarta");
        searchService.getProducts(requestDTO);

    }

    @Test
    public void testGetProductException() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resource/search.mock"), Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:Jakarta")).thenThrow(NullPointerException.class);


        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("Jakarta");
        SearchResponseDTO response = searchService.getProducts(requestDTO);

        assertEquals(response.getProducts().size(), 10);
        assertEquals(response.getLocationBasedProducts(), null);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:Jakarta");
    }

}