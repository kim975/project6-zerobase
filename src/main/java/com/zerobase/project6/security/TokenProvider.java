package com.zerobase.project6.security;

import com.zerobase.project6.user.domain.model.common.UserType;
import com.zerobase.project6.user.service.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final SecretKey secretKey;

    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final String KEY_ROLES = "roles";
    private LoginService loginService;

    public String generateToken(UserSecurity userSecurity) {

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put(KEY_ROLES, userSecurity.getUserRoles());
        claims.put("userType", userSecurity.getUserType());

        return Jwts.builder()
                .subject(userSecurity.getUserToken())
                .claims(claims)
                .issuedAt(now) // 토큰 생성 시간
                .expiration(expiredDate) // 토큰 만료 시간
                .signWith(secretKey) // 사용할 암호화 알고리즘, 비밀키
                .compact();

    }

    public String getUserToken(String token) {
        return parseClaims(token).getSubject();
    }

    public UserType getUserType(String token) {
        return UserType.valueOf(parseClaims(token).get("userType").toString());
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

         return !parseClaims(token).getExpiration().before(new Date());

    }

    private Claims parseClaims(String token) {

        try {
            return  Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = loginService.loadUserByUserToken(getUserToken(jwt), getUserType(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
