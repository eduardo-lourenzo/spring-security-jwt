package br.edu.zup.spring_security_jwt.controllers;

import br.edu.zup.spring_security_jwt.services.AuthService;
import br.edu.zup.spring_security_jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Map<String, String>> getUser() {
        UserService userService = new UserService();
        return new ResponseEntity<>(
                userService.getUser(authService.getUser()),
                HttpStatus.OK
        );
    }
}