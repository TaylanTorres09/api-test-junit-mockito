package br.com.api.test.junit_mockito.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    
    @NotEmpty(message = "Nome obrigatório")
    private String name;
    
    @NotEmpty(message = "Email obrigatório")
    @Email
    private String email;
    
    @NotEmpty(message = "Senha obrigatória")
    private String password;

}
