package br.edu.zup.spring_security_jwt.dtos;

import br.edu.zup.spring_security_jwt.models.RoleEntity;
import br.edu.zup.spring_security_jwt.models.RoleEnum;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<RoleEnum> roles;
}
