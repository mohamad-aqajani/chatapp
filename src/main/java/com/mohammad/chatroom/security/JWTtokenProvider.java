package com.mohammad.chatroom.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTtokenProvider {

    @Value("${jwtExpirationTime}")
    private Long expirationTime;

    @Value("${jwtPrivateKey}")
    private String jwtSecretPrivate;

    public String generateToken(String username) {
        try {
            Long currentTime = new Date().getTime();
            return JWT.create().withSubject(username)
                    .withIssuedAt(new Date(currentTime))
                    .withExpiresAt(new Date(currentTime + expirationTime))
                    .sign(getAlgorithm());
        } catch (JWTCreationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while generating token");
        }
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(getAlgorithm()).build().verify(token);
            return true;
        } catch (JWTVerificationException exeption) {
            exeption.printStackTrace();
            throw new RuntimeException("Error while generating token");
        }
    }


    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtSecretPrivate);
    }
}