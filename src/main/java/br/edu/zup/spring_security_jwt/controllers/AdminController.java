package br.edu.zup.spring_security_jwt.controllers;

import br.edu.zup.spring_security_jwt.services.AdminService;
import br.edu.zup.spring_security_jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Map<String, String>> getAdmin() {
        AdminService adminService = new AdminService();
        return new ResponseEntity<>(
                adminService.getAdmin(authService.getUser()),
                HttpStatus.OK
        );
    }
}
