package kz.reserve.backend.security.jwt;

import io.jsonwebtoken.*;
import kz.reserve.backend.configuration.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    @Value("${auth.jwt.expiration-time}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
            System.out.println(e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired");
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is not supported");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
            System.out.println(e.getMessage());
        }

        return false;
    }
}
