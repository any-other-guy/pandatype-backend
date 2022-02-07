package com.pandatype.auth.security;

import com.pandatype.auth.entities.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {
    final Logger LOGGER = LoggerFactory.getLogger(TokenUtils.class);

    private static final Long EXPIRATION = 604800L;

    private static UserEntity userEntity;

    public String createToken(UserEntity userEntity) {
        try {
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);

            String token = Jwts.builder()
                    .setIssuer("SpringBoot")
                    .setAudience(userEntity.getEmail())
                    .setExpiration(expirationDate)
                    .setIssuedAt(new Date())
//                    .claim("role", userEntity.getUserRole())
                    .signWith(RsaUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
            return String.format("Bearer %s", token);
        } catch (Exception e) {
            return null;
        }
    }

    public UserEntity validationToken(String token) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(RsaUtils.getPublicKey())
                    .build().parseClaimsJws(token).getBody();
            assert claims != null;
            Date expiration = claims.getExpiration();
            if (!expiration.after(new Date())) {
                return null;
            }
            userEntity = new UserEntity();
            userEntity.setEmail(claims.getAudience());
            return userEntity;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return null;
    }
}
