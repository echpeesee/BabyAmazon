package com.baby.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	//https://datatracker.ietf.org/doc/html/rfc7231#section-6.6.1   reference for all the status codes
	 @ExceptionHandler({Exception.class})
	    public ResponseEntity<Object> handleStudentNotFoundException(Exception e) {
		 //System.out.println(e.getMessage());
		 if(e  instanceof BadCredentialsException) {
			 return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(e.getMessage());
		 }
		 
		 if(e  instanceof AccessDeniedException) {
			 return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(e.getMessage());
		 }
		 
		 if(e  instanceof SignatureException) {
			 return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(e.getMessage());
		 }
		 
		 if(e  instanceof ExpiredJwtException) {
			 return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(e.getMessage());
		 }
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(e.getMessage());
	    }
}
