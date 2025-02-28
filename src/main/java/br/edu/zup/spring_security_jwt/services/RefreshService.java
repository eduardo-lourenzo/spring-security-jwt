package br.edu.zup.spring_security_jwt.services;

import br.edu.zup.spring_security_jwt.dtos.TokenResponseDTO;
import br.edu.zup.spring_security_jwt.infras.jwt.TokenProviderJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshService {

    @Autowired
    private TokenProviderJWT tokenProviderJWT;

    public TokenResponseDTO refreshToken(String authorizationHeader) {
        String oldToken = authorizationHeader.replace("Bearer ", "");

        String newToken = tokenProviderJWT.refreshToken(oldToken);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(newToken);

        return tokenResponseDTO;
    }
}