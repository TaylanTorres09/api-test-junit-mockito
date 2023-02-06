package br.com.api.test.junit_mockito.controllers.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.api.test.junit_mockito.services.exceptions.DataIntegrityViolationException;
import br.com.api.test.junit_mockito.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = controllerExceptionHandler.objectNotFound(
            new ObjectNotFoundException("Object not found"), new MockHttpServletRequest());
        
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Object not found", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void whenDataIntegrityViolationThenReturnResponseEntity() {

        ResponseEntity<StandardError> response = controllerExceptionHandler.dataIntegrityViolation(
            new DataIntegrityViolationException("E-mail already registered"), new MockHttpServletRequest());
        
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("E-mail already registered", response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());

    }

}
