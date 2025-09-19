package com.codifica.compti.security;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.codifica.compti.models.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT para autenticação do usuário.
     *
     * @param user o usuário para o qual o token será gerado
     * @return o token JWT como uma string
     */
    public String generateToken(User user) {
        return generateToken(user.getEmail(), 2); // Expira em 2 horas
    }



    /**
     * Gera um token JWT com um assunto e tempo de expiração especificados.
     *
     * @param subject       o assunto (subject) do token, geralmente o login do usuário
     * @param hoursToExpire o número de horas até o token expirar
     * @return o token JWT gerado
     * @throws RuntimeException se houver um erro na geração do token
     */
    public String generateToken(String subject, int hoursToExpire) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("finalproject")
                    .withSubject(subject)
                    .withExpiresAt(genExpirationDate(hoursToExpire))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    /**
     * Gera a data de expiração do token JWT com base nas horas especificadas.
     *
     * @param hours o número de horas até o token expirar
     * @return a data de expiração como um objeto {@link Instant}
     */
    private Instant genExpirationDate(int hours) {

        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-03:00"));
    }

    /**
     * Valida um token JWT e retorna o assunto (subject) associado.
     *
     * @param token o token JWT a ser validado
     * @return o assunto (subject) do token se for válido
     * @throws RuntimeException se o token for inválido ou expirado
     */

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("finalproject")
                    .build()
                    .verify(token)
                    .getSubject()
                    ;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }
}
