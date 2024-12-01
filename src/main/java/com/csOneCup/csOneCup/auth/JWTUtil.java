package com.csOneCup.csOneCup.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {
    private final SecretKey secretKey;
    private static String staticSecretKey;

    public JWTUtil(@Value("${jwt.secret}")String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        JWTUtil.staticSecretKey = secret; // static 필드 초기화
    }

    public String getUserid(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public static String getOnlyToken(String token) {
        return token.split(" ")[1];
    }

    public String createJWT(String username, Long expireMs) {
        System.out.println(username);
        return Jwts.builder()
                .claim("username", username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expireMs))
                .signWith(secretKey)
                .compact();
    }

    public static String extractUserId(String token) {
        token = getOnlyToken(token);
        SecretKey noStringSecret = new SecretKeySpec(staticSecretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        return Jwts.parser().verifyWith(noStringSecret).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }
}
