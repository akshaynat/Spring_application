package com.example.spring_session.service.impl;

import com.example.spring_session.dto.MyRequestDTO;
import com.example.spring_session.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("Inside userService constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inside UserService post constructor");
    }

    @Override
    public boolean updateEmployee(MyRequestDTO request, String id) {
        System.out.println("Inside user service" + request + "");
        return false;
    }
}
