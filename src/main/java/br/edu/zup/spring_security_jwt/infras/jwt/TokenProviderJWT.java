package br.edu.zup.spring_security_jwt.infras.jwt;

import br.edu.zup.spring_security_jwt.models.RoleEntity;
import br.edu.zup.spring_security_jwt.models.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProviderJWT {
    static final long FIVE_MIN_IN_MS = 300000; // 5min => 300s => 300.000ms
    private final String secretKeyJWT = System.getenv("JWT_SECRETKEY");

    // Passar Role e Claim como parâmetros para montar o token
    // Service de Login Monta o token
    // As rotas de admin e user não pre checa a autenticação
    // usar getRoles e getClaim no Token ou BD?

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDateInMilliseconds = new Date(currentDate.getTime() + FIVE_MIN_IN_MS);
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        return Jwts.builder()
                .subject(username)
                .claim("department", userEntity.getDepartment())
                .claim("roles", userEntity.getRoles()
                        .stream()
                        .map(RoleEntity::getType)
                        .collect(Collectors.toList())
                )
                .issuedAt(currentDate)
                .expiration(expirationDateInMilliseconds)
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyJWT));
    }

    public String getUsername(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;
    }

    // Refresh Token method
}
