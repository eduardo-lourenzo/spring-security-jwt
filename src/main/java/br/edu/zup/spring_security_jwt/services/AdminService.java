package br.edu.zup.spring_security_jwt.services;

import br.edu.zup.spring_security_jwt.models.UserEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    public Map<String, String> getAdmin(UserEntity userEntity) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bem-vindo administrador " + userEntity.getName() + "!");
        response.put("department", userEntity.getDepartment());
        return response;
    }
}