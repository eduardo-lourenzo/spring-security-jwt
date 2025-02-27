package br.edu.zup.spring_security_jwt.controllers;

import br.edu.zup.spring_security_jwt.dtos.RegisterUserDTO;
import br.edu.zup.spring_security_jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
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
