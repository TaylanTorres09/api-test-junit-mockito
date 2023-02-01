package br.com.api.test.junit_mockito.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError {
    
    private Long timestamp;
    private Integer status;
    private String error;

}
