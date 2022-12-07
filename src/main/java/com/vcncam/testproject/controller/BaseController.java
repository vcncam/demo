package com.vcncam.testproject.controller;

import com.vcncam.testproject.payload.GeneralResponse;
import com.vcncam.testproject.payload.GeneralStatusResponse;
import com.vcncam.testproject.util.Constant;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public abstract class BaseController {
    
    public <T> ResponseEntity<GeneralResponse<T>> success (T data) {
        GeneralStatusResponse status = GeneralStatusResponse.builder()
            .code(Constant.ResponseCode.SUCCESS)
            .message(Constant.ResponseMessage.SUCCESS)
            .timestamp(LocalDateTime.now())
            .build();
        
        GeneralResponse<T> response = new GeneralResponse<>();
        
        response.setStatus(status);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
    
    public <T> ResponseEntity<GeneralResponse<T>> success() {
        GeneralStatusResponse status = GeneralStatusResponse.builder()
            .code(Constant.ResponseCode.SUCCESS)
            .message(Constant.ResponseMessage.SUCCESS)
            .timestamp(LocalDateTime.now())
            .build();
        
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setStatus(status);
        return ResponseEntity.ok(response);
    }
    
    public <T> ResponseEntity<GeneralResponse<T>> failed() {
        GeneralStatusResponse status = GeneralStatusResponse.builder()
            .code(Constant.ResponseCode.ERROR)
            .message(Constant.ResponseMessage.ERROR)
            .timestamp(LocalDateTime.now())
            .build();
        
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setStatus(status);
        return ResponseEntity.ok(response);
    }
}
