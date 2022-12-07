package com.vcncam.testproject.controller;

import com.vcncam.testproject.model.User;
import com.vcncam.testproject.payload.GeneralResponse;
import com.vcncam.testproject.payload.GeneralStatusResponse;
import com.vcncam.testproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping (path = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/all-users")
    public ResponseEntity<GeneralResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        GeneralStatusResponse generalStatusResponse = GeneralStatusResponse
            .builder()
            .code("000")
            .message("SUCCESS")
            .timestamp(LocalDateTime.now())
            .build();
        GeneralResponse<List<User>> response = new GeneralResponse<>();
        response.setStatus(generalStatusResponse);
        response.setData(users);
        return ResponseEntity.ok(response);
    }
}
