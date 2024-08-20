package br.com.almavivasolutions.integrador.model.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.almavivasolutions.integrador.utils.logger.ApiLogger;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<String> buildErrorResponse(HttpStatus status, String message) {
        HttpResponseMessage errorResponse = new HttpResponseMessage(
                status.value(),
                message,
                System.currentTimeMillis()
        );
        ApiLogger.logRequestError(message);
        return ResponseEntity
        		.status(status)
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(errorResponse.toString());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidHeaderException.class)
    public ResponseEntity<String> handleInvalidHeaderException(InvalidHeaderException ex){
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
    	return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno.");
    }
}
