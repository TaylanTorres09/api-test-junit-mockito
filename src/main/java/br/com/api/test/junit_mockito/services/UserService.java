package br.com.api.test.junit_mockito.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.test.junit_mockito.dtos.UserDTO;
import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.repositories.UserRepository;
import br.com.api.test.junit_mockito.services.exceptions.DataIntegrityViolationException;
import br.com.api.test.junit_mockito.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User id: " + id + ", not Found."));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User create(UserDTO userDTO) {
        this.findByEmail(userDTO);
        return this.userRepository.save(mapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO userDTO) {
        Optional<User> user = this.userRepository.findByEmail(userDTO.getEmail());
        if(user.isPresent())
            throw new DataIntegrityViolationException("Email já cadastrado");
    }

    public User update(UserDTO userDTO, Long id) {
        this.findById(id);
        userDTO.setId(id);
        return this.userRepository.save(mapper.map(userDTO, User.class));
    }

}
