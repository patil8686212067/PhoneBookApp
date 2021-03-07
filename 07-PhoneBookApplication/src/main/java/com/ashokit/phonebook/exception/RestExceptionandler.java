package com.ashokit.phonebook.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//@ControllerAdvice
public class RestExceptionandler {
	
	
	@ExceptionHandler(value=NoContactFoundException.class)
	public ResponseEntity<ApiError> handleNoContactFounException(){
		
		ApiError apiError = new ApiError(404,"No Contact Found",new Date());
		
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
	}

}
