package br.com.api.test.junit_mockito.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.test.junit_mockito.models.User;
import br.com.api.test.junit_mockito.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired 
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

}
