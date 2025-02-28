package br.edu.zup.spring_security_jwt.dtos;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private String accessToken;
}
