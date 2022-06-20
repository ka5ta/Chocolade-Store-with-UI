package com.gridu.store.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/*@Service
public class JwtTokenService {

    private final static int tokenExpirationTime = 10 * 60 * 1000;
    private final static String tokenKey = "secret";
    static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .signWith(key)
                .compact();
    }

    public static Claims decodeToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }

}
*/