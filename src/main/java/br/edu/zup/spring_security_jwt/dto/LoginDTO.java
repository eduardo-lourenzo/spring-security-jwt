package br.edu.zup.spring_security_jwt.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}