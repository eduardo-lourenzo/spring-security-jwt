package br.edu.zup.spring_security_jwt.services;


import br.edu.zup.spring_security_jwt.dtos.RegisterUserDTO;
import br.edu.zup.spring_security_jwt.models.RoleEntity;
import br.edu.zup.spring_security_jwt.models.UserEntity;
import br.edu.zup.spring_security_jwt.repositories.RoleRepository;
import br.edu.zup.spring_security_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUsername(registerUserDTO.getUsername())) {
            throw new RuntimeException("Unprocess Entity");
        }


        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerUserDTO.getName());
        userEntity.setUsername(registerUserDTO.getUsername());
        userEntity.setEmail(registerUserDTO.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerUserDTO.getPassword()));

        Set<RoleEntity> rolesEntities = registerUserDTO.getRoles()
                .stream()
                .map(RoleEntity::new)
                .collect(Collectors.toSet());

        roleRepository.saveAll(rolesEntities);

        userEntity.setRoles(rolesEntities);

        userRepository.save(userEntity);
    }
}