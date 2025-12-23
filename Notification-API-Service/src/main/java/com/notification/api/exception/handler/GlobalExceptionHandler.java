package com.notification.api.exception.handler;

import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.common.sdk.models.enums.APIResponseCode;
import com.common.sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.services.ResponseHandler;
import com.notification.api.exception.AbstractException;
import com.notification.api.exception.ResourceNotFoundException;
import com.notification.api.exception.ValidationException;
import com.notification.api.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	
	
	private final ResponseHandler responseHandler;
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
   public GenericApiResponse<Void> handleValidationException(ValidationException exception){
		return responseHandler.failure(Objects.requireNonNullElse(exception.getStatusCode(),APIResponseCode.BAD_REQUEST.getCode()),exception.getMessage());
   }  
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	   public GenericApiResponse<Void> handleResourceNotFoundException(ResourceNotFoundException exception){

		return responseHandler.failure(Objects.requireNonNullElse(exception.getStatusCode(),APIResponseCode.BAD_REQUEST.getCode()),exception.getMessage());	
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
