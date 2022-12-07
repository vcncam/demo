package com.vcncam.testproject.config;

import com.vcncam.testproject.exception.CustomException;
import com.vcncam.testproject.payload.GeneralResponse;
import com.vcncam.testproject.payload.GeneralStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler (value = {CustomException.class})
    @ResponseBody
    public ResponseEntity<GeneralResponse> handleGeneralCustomException(CustomException exception) {
        GeneralStatusResponse generalStatusResponse = GeneralStatusResponse
            .builder()
            .code(exception.getErrorCode())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
        GeneralResponse response = new GeneralResponse<>();
        response.setStatus(generalStatusResponse);
        return ResponseEntity.ok(response);
    }
}
