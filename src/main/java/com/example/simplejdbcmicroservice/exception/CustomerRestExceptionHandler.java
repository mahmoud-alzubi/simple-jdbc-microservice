package com.example.simplejdbcmicroservice.exception;

import com.example.simplejdbcmicroservice.response.CustomerErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());


    ////////////////////////////////////////////////////////////

    /**
     * @param e
     * @return
     */
    @ExceptionHandler
    ResponseEntity<?> handleException(CustomerNotFoundException e) {
        CustomerErrorResponse errorResponse = new CustomerErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        logger.error(errorResponse.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    ////////////////////////////////////////////////////////////

    /**
     * @param e
     * @return
     */
    @ExceptionHandler
    ResponseEntity<?> handleException(Exception e) {
        CustomerErrorResponse errorResponse = new CustomerErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        logger.warn(errorResponse.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
