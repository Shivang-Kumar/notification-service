package com.notification.api.exception.handler;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.notification.api.exception.AbstractException;
import com.notification.api.exception.ResourceNotFoundException;
import com.notification.api.exception.ValidationException;
import com.notification.api.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ValidationException.class)
   public ResponseEntity<String> handleValidationException(ValidationException exception){
		return genericExceptionHandler(exception, ()->{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getErrorMessage());
		});
		
   }  
	@ExceptionHandler(ResourceNotFoundException.class)
	   public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){

		return genericExceptionHandler(exception, ()->{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getErrorMessage());
		});
	
		
	}
	
	public ResponseEntity<String> genericExceptionHandler(AbstractException exception, Supplier<ResponseEntity<String>> runner)
	{
		if(CommonUtils.isNotEmpty(exception.getStatusCode()))
		{
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getErrorMessage());
		}
		
		return runner.get();
	}

}
