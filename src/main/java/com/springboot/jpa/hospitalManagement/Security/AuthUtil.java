package com.springboot.jpa.hospitalManagement.Security;

import com.springboot.jpa.hospitalManagement.entity.Hospital;
import com.springboot.jpa.hospitalManagement.entity.type.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthUtil {

    @Value("${jwt.secretKey}") // from appication properties
    private String jwtSecretKey;


    /*Converts your secret string into a SecretKey object.

            Keys.hmacShaKeyFor() → generates an HMAC-SHA key for signing the token.

            StandardCharsets.UTF_8 → ensures consistent byte encoding.*/
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Hospital hospital) {
        return Jwts.builder()
                .setSubject(hospital.getUsername()) // username
                .claim("hospitalId", hospital.getId().toString()) // custom claim
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public AuthProviderType getProviderTypeFromRegistrationId(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google" -> AuthProviderType.GOOGLE;
            case "github" -> AuthProviderType.GITHUB;
            case "facebook" -> AuthProviderType.FACEBOOK;
            default -> throw new IllegalArgumentException("Unsupported OAuth2 provider ; " + registrationId);
        };
    }


    public String determineProviderIdfromOAuth2User(OAuth2User oAuth2User , String registrationId){
        String providerId = switch (registrationId.toLowerCase()){
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
          // add casse for facebook
            default -> {
                log.error("Unsupported OAuth provider : {}" , registrationId);
                throw new IllegalArgumentException("Unsupported OAuth provider : " + registrationId);
                
            }
        };
        
        if(providerId == null || providerId.isBlank()){
            log.error("Unable to determine providedId for provoder :  {}", registrationId);
            throw new IllegalArgumentException("unable to determine providerId for OAuth  login");
            
        }
        return providerId;
    }

    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User, String registrationId, String providerId) {
        String email = oAuth2User.getAttribute("email");
        if (email != null && !email.isBlank()) {
            return email;
        }
        return switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }
}
