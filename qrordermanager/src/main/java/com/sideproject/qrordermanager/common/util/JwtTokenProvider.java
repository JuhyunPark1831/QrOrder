package com.sideproject.qrordermanager.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secret-key}")
    public String SECRET_KEY;
    @Value("${spring.jwt.access-token-expire}")
    private Long ACCESS_TOKEN_EXPIRE_LENGTH;
    @Value("${spring.jwt.refresh-token-expire}")
    private Long REFRESH_TOKEN_EXPIRE_LENGTH;

    private final RedisUtil redisUtil;

    public String createAccessToken(String acLoginId) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .claim("acLoginId", acLoginId)
                .claim("type", "ACCESS")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken(String acLoginId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH);

        String refreshToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, String.valueOf(SECRET_KEY))
                .claim("acLoginId", acLoginId)
                .claim("type", "REFRESH")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();

        // Redis에 토큰 저장
        redisUtil.setData(acLoginId, refreshToken, REFRESH_TOKEN_EXPIRE_LENGTH);

        return refreshToken;
    }
}
