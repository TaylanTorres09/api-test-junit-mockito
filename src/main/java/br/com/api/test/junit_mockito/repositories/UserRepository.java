package br.com.api.test.junit_mockito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.test.junit_mockito.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
