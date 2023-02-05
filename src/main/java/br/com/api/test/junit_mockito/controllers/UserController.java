package br.com.api.test.junit_mockito.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.test.junit_mockito.dtos.UserDTO;
import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.services.UserService;
import jakarta.validation.Valid;

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
        List<UserDTO> userDTOs = users.stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        User user = this.userService.create(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/{id}").buildAndExpand(user.getId()).toUri();
        
        //return uri in headers
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
        User user = this.userService.update(userDTO, id);

        return new ResponseEntity<UserDTO>(mapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
