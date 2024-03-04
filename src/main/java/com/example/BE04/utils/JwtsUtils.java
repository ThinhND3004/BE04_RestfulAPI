package com.example.BE04.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtsUtils {
    @Value("${key.value.token}")
    private String strKey;

    public String createToken(String data)
    {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.builder().subject(data).signWith(key).compact();
    }

    public String descriptToken(String token)
    {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        String data = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();

        return data;
    }
}
