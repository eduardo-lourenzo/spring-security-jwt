package br.edu.zup.spring_security_jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    //@Autowired
    // private UserService userService;

//    @PreAuthorize("hasRole('USER')")
//    @GetMapping
//    public void getUser(@RequestBody RegisterUserDto registerUserDto){
//        // return userService.getUser(registerUserDto);
//    }

//    @PreAuthorize("hasRole('USER')")
//    @GetMapping
//    public ResponseEntity<UserResponseDTO> getUser() {
//        // Apenas chama o service
//        // UserResponseDTO userResponse = userService.getUser();
//        // return ResponseEntity.ok(userResponse);
//    }
}
/*
Service
No service, você pode acessar o contexto de segurança para obter o usuário autenticado. O Spring Security armazena o Authentication no contexto, que contém as informações do token e do usuário.



@Service
public class UserService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // Classe que valida e extrai informações do token

    public UserResponseDTO getUser() {
        // Obtém o contexto de segurança
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extrai o username do contexto
        String username = authentication.getName();

        // Aqui você pode buscar o usuário no banco de dados ou realizar outras operações
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setUsername(username);
        userResponse.setMessage("Usuário autenticado com sucesso!");
        return userResponse;
    }
}
 */