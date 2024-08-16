package br.com.almavivasolutions.integrador.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<String> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                System.currentTimeMillis()
        );
        return ResponseEntity
        		.status(status)
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(errorResponse.toString());
        //return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("{\"error\": \"Resource not found\"}");
//    }
}
