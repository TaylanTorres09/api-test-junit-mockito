package br.com.api.test.junit_mockito.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.api.test.junit_mockito.dtos.UserDTO;
import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.services.UserService;

public class UserControllerTest {

    private static final Long ID = Long.valueOf(1);
    
    private static final String name = "Donatello";
    
    private static final String email = "donatello@gmail.com";
    
    private static final String password = "12345";

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdReturnStatusOK() {
        when(userService.findById(anyLong())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(name, response.getBody().getName());
        assertEquals(email, response.getBody().getEmail());
        assertEquals(password, response.getBody().getPassword());
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = userController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());

        UserDTO firstElement = response.getBody().get(0);

        assertEquals(UserDTO.class, firstElement.getClass());
        assertEquals(ID, firstElement.getId());
        assertEquals(name, firstElement.getName());
        assertEquals(email, firstElement.getEmail());
        assertEquals(password, firstElement.getPassword());
    }

    @Test
    void testCreate() {

    }

    @Test
    void testDelete() {

    }



    @Test
    void testUpdate() {

    }

    private void startUser() {
        user = new User(ID, name, email, password);
        userDTO = new UserDTO(ID, name, email, password);
    }
}
