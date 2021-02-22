package com.example.spring_session.contoller;

import com.example.spring_session.dto.MyRequestDTO;
import com.example.spring_session.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {


    public UserController(UserService user) {
        this.user = user;
        System.out.println("Inside UserController constructor...");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inside UserController post construct");
    }

    @Autowired
    private UserService user;

    @PostMapping(path = "/update/employee/{id}")
    public boolean updateEmployeeId(@PathVariable String id, @RequestBody MyRequestDTO request) {
        return user.updateEmployee(request, id);
    }

}
