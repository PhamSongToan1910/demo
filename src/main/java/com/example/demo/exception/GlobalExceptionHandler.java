package com.example.demo.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({BadRequestException.class, NotFoundException.class, AccountNotFoundException.class, RoleNotFoundException.class, CoursesNotFoundException.class, FileNotFoundException.class, LessonsNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    	return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
