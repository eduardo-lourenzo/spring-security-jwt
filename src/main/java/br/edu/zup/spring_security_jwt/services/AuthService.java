package br.edu.zup.spring_security_jwt.services;


import br.edu.zup.spring_security_jwt.dtos.RegisterUserDTO;
import br.edu.zup.spring_security_jwt.models.RoleEntity;
import br.edu.zup.spring_security_jwt.models.UserEntity;
import br.edu.zup.spring_security_jwt.repositories.RoleRepository;
import br.edu.zup.spring_security_jwt.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUsername(registerUserDTO.getUsername())) {
            return false;
            // throw new RuntimeException("Unprocess Entity");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerUserDTO.getName());
        userEntity.setUsername(registerUserDTO.getUsername());
        userEntity.setEmail(registerUserDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        userEntity.setDepartment(registerUserDTO.getDepartment());

        Set<RoleEntity> rolesEntities = registerUserDTO.getRoles()
                .stream()
                .map(roleEnum -> roleRepository.findByType(roleEnum)
                        .orElseGet(() -> roleRepository.save(
                                new RoleEntity(roleEnum))
                                // Salva um novo papel que não existe no B.D.
                        )
                )
                .collect(Collectors.toSet());

        userEntity.setRoles(rolesEntities);
        userRepository.save(userEntity);
        return true;
    }

    public UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Not Found Entity")
                );
    }
}