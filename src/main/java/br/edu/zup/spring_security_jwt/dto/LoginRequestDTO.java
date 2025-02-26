package br.edu.zup.spring_security_jwt.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}