package com.example.demo.exception;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private int statusCode;
    private ZonedDateTime timestamp;
    private String message;
    
}
