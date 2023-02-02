package br.com.api.test.junit_mockito.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.test.junit_mockito.dtos.UserDTO;
import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return new ResponseEntity<UserDTO>(mapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = this.userService.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
        return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
    }
    
}
