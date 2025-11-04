package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

public class ControllerExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);
    private static ResponseEntity<Map<String, String>> buildResponse(String ex, HttpStatus serviceUnavailable) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex);
        return ResponseEntity.status(serviceUnavailable).body(response);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        logger.info("RuntimeException: {}", ex.getMessage(), ex);
        buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
        return null;
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Map<String, String>> handleRestClientException(RestClientException ex) {
        logger.info("RestClientException: {}", ex.getMessage(), ex);
        buildResponse(ex.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOtherExceptions(Exception ex) {
        logger.info("Unhandled exception: {}", ex.getMessage(), ex);
        buildResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
        logger.info("Unhandled exception: {}", ex.getMessage(), ex);
        buildResponse(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return null;
    }

}
