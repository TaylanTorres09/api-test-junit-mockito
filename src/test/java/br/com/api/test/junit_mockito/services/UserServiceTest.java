package br.com.api.test.junit_mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.api.test.junit_mockito.dtos.UserDTO;
import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.repositories.UserRepository;
import br.com.api.test.junit_mockito.services.exceptions.DataIntegrityViolationException;
import br.com.api.test.junit_mockito.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceTest {

    private static final Long ID = Long.valueOf(1);
    
    private static final String name = "Donatello";
    
    private static final String email = "donatello@gmail.com";
    
    private static final String password = "12345";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        // Iniciar Mocks da classe
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(name, response.getName());
        assertEquals(email, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("User id: " + anyLong() + ", not Found."));

        try {
            userService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("User id: " + anyLong() + ", not Found.", e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userRepository.findAll();

        
        assertNotNull(users);
        assertEquals(1, users.size());
        User firstElement = users.get(0);
        assertEquals(User.class, firstElement.getClass());
        
        assertEquals(ID, firstElement.getId());
        assertEquals(name, firstElement.getName());
        assertEquals(email, firstElement.getEmail());
        assertEquals(password, firstElement.getPassword());
    }

    @Test
    void whenCreateThenReturnUser() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(name, response.getName());
        assertEquals(email, response.getEmail());
        assertEquals(password, response.getPassword());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(Long.valueOf(2));
            userService.create(userDTO);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Email já cadastrado", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnUser() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(name, response.getName());
        assertEquals(email, response.getEmail());
        assertEquals(password, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(Long.valueOf(2));
            userService.create(userDTO);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Email já cadastrado", e.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyLong());
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("User id: " + anyLong() + ", not Found."));

        try {
            userService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("User id: " + anyLong() + ", not Found.", e.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, name, email, password);
        userDTO = new UserDTO(ID, name, email, password);
        optionalUser = Optional.of(new User(ID, name, email, password));
    }

}
