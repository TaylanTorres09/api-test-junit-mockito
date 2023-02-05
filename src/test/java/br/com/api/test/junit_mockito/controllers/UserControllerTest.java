package br.com.api.test.junit_mockito.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import br.com.api.test.junit_mockito.dtos.UserDTO;
import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.services.UserService;

public class UserControllerTest {

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
    }

    @Test
    void testCreate() {

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindById() {

    }

    @Test
    void testUpdate() {

    }
}
