package br.com.api.test.junit_mockito.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.api.test.junit_mockito.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.ServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,  ServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<StandardError>(error, HttpStatus.NOT_FOUND);
    }

}
