package br.com.api.test.junit_mockito.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.repositories.UserRepository;
import br.com.api.test.junit_mockito.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository;

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User id: " + id + ", not Found."));
    }

}
