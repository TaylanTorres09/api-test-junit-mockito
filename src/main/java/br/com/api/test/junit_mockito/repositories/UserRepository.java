package br.com.api.test.junit_mockito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.test.junit_mockito.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
