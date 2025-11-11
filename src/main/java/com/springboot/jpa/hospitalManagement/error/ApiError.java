package com.springboot.jpa.hospitalManagement.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStrap;
    private String error;
    private HttpStatus statusCode;

    public ApiError(){
        this.timeStrap = LocalDateTime.now();
    }

    public ApiError(String error , HttpStatus statusCode){
        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}
