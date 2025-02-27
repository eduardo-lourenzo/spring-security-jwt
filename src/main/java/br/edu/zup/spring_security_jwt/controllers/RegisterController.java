package br.edu.zup.spring_security_jwt.controllers;

import br.edu.zup.spring_security_jwt.dtos.RegisterUserDTO;
import br.edu.zup.spring_security_jwt.services.AdminService;
import br.edu.zup.spring_security_jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<String> getAdmin(@RequestBody RegisterUserDTO registerUserDTO) {
        if (authService.registerUser(registerUserDTO)) {
            return new ResponseEntity<>(
                    "Usuário cadastrado com sucesso!",
                    HttpStatus.CREATED
            );
        }

        return new ResponseEntity<>(
                "O usuário não pode ser cadastrado!",
                HttpStatus.CONFLICT
        );
    }
}
