package com.example.spring_session.service;

import com.example.spring_session.dto.MyRequestDTO;

public interface UserService {
    boolean updateEmployee(MyRequestDTO request, String id);
}