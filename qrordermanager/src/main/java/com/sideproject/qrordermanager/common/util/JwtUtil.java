package com.sideproject.qrordermanager.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${spring.jwt.secret-key}")
    public String SECRET_KEY;

    public String getAcLoginIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("acLoginId").toString();
    }

    public String getTypeIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("type").toString();
    }

    public void validateToken(String token) {
        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }
}
