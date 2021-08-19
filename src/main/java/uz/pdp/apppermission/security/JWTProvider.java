package uz.pdp.apppermission.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import uz.pdp.apppermission.domain.Role;

import java.util.Collection;
import java.util.Date;

@Component
public class JWTProvider {
    private static final long EXPIRE_TIME = 1000 * 60 * 60L;
    private static final String KEY = "secretKeyForEnterToCabinet";

    public String generateToken(String login, Role role) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        return Jwts
                .builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("role", role.getName())
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public String getLoginFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
