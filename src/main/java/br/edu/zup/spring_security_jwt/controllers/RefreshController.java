package br.edu.zup.spring_security_jwt.controllers;

import br.edu.zup.spring_security_jwt.dtos.TokenResponseDTO;
import br.edu.zup.spring_security_jwt.services.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refresh")
public class RefreshController {

    @Autowired
    private RefreshService refreshService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        TokenResponseDTO tokenResponseDTO = refreshService.refreshToken(authorizationHeader);
        return ResponseEntity.ok(tokenResponseDTO);
    }
}
