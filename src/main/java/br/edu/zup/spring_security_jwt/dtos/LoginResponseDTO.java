package br.edu.zup.spring_security_jwt.dtos;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String accessToken;
}
