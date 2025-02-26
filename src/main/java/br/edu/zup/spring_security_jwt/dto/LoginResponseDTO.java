package br.edu.zup.spring_security_jwt.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String accessToken;
}
