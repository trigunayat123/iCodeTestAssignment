package com.icodeTestAssignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icodeTestAssignment.entities.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiResponse> nullPointeExceptionHandler(NullPointerException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse> messageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
		String message = ex.getMessage();
		String customMessage = "Fields and values are missing : Please define some fields and values";
		ApiResponse apiResponse = new ApiResponse(customMessage);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}  
