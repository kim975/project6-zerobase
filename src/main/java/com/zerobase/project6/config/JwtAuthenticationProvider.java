package com.zerobase.project6.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtAuthenticationProvider {

    private final String secretKeyStr = "ZGF5b25lLXNwcmluZy1ib290LWRpdmlkZW5kLXByb2plY3QtdHV0b3JpYWwtand0LXNlY3JlY3Qta2V5";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyStr));

    private final long tokenValidTime = 1000L * 60 * 60 * 24;

//    public String createToken(String userPk, Long id, UserType userType) {
//
//        Date now = new Date();
//        Date expiredDate = new Date(now.getTime() + tokenValidTime);
//
//        return Jwts.builder()
//                .subject(Aes256Util.encrypt(userPk))
//                .setId(Aes256Util.encrypt(id.toString()))
//                .claim("roles", userType)
//                .issuedAt(now)
//                .expiration(expiredDate)
//                .signWith(secretKey)
//                .compact();
//
//    }
//
//    public boolean validateToken(String token) {
//        if (!StringUtils.hasText(token)) {
//            return false;
//        }
//
//        return !parseClaims(token).getExpiration().before(new Date());
//
//    }
//
//    private Claims parseClaims(String token) {
//
//        try {
//            return  Jwts.parser()
//                    .verifyWith(secretKey)
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        }
//    }
//
//    public UserVO getUserVo(String token) {
//        Claims claims = parseClaims(token);
//
//        return UserVO.builder()
//                .id(Long.valueOf(Aes256Util.decrypt(claims.getId())))
//                .email(Aes256Util.decrypt(claims.getSubject()))
//                .build();
//    }

}
