package com.example.spring_session.contoller;

import com.example.spring_session.dto.MyRequestDTO;
import com.example.spring_session.dto.MyResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Success!!";
    }

    @PostMapping(path = "/bye")
    public String helloWorldPost() {
        return "Done";
    }

//    @PutMapping(path = "/put")
//    @DeleteMapping

    @PostMapping(path = "/details")
    public String requestUser(@RequestBody MyRequestDTO req){
        return req.toString();
    }

    @GetMapping(path = "/employee/{employeeid}")
    public String employeedetails(@PathVariable int employeeid) {
        MyResponseDTO res = new MyResponseDTO();
        res.setId(employeeid);
        return "Your id is :" + res.getId();
    }
}
