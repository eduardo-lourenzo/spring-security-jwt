package br.edu.zup.spring_security_jwt.services;

import br.edu.zup.spring_security_jwt.models.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    public Map<String, String> getUser(UserEntity userEntity) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bem-vindo usuário " + userEntity.getName() + "!");
        response.put("department", userEntity.getDepartment());
        return response;
    }
}