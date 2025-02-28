package br.edu.zup.spring_security_jwt.controllers;

import br.edu.zup.spring_security_jwt.dtos.LoginRequestDTO;
import br.edu.zup.spring_security_jwt.dtos.TokenResponseDTO;
import br.edu.zup.spring_security_jwt.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

     @Autowired
     private LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
         String token = loginService.login(loginRequestDTO);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(token);

        return new ResponseEntity<>(tokenResponseDTO, HttpStatus.OK);
    }
}
