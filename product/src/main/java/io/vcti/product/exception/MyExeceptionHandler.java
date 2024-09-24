package io.vcti.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class MyExeceptionHandler {

	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<String> exceptionMethod(EntityNotFoundException e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> globalException(Exception e)
	{
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
