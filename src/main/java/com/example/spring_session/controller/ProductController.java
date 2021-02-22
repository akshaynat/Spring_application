package com.example.spring_session.controller;

import com.example.spring_session.dto.ProductRequestDTO;
import com.example.spring_session.dto.SearchResponseDTO;
import com.example.spring_session.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private SearchService service;

    @PostMapping(path = "/search")
    public SearchResponseDTO mySearch(@RequestBody ProductRequestDTO request) {
        return service.getProducts(request);
    }
}