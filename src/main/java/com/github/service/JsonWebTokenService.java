package com.github.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.modal.Account;
import com.github.security.JWTUtils;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class JsonWebTokenService {
    private final String ISSUER;
    private final Algorithm ALGORITHM;
    private final int EXPIRE_TIME_IN_SECONDS;
    private final JWTVerifier VERIFIER;


    @Inject
    public JsonWebTokenService() {
        String SECRET = JWTUtils.SECRET;
        this.ISSUER = JWTUtils.ISSUER;
        this.ALGORITHM = Algorithm.HMAC256(SECRET);
        this.VERIFIER = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
        this.EXPIRE_TIME_IN_SECONDS = JWTUtils.EXPIRE_SECONDS;
    }

    public boolean isValid(String token) {
        try {
            VERIFIER.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public DecodedJWT decodeJwt(String token) {
        return VERIFIER.verify(token);
    }


    public String createJwt(Account user) {
        Date expireDate = createExpireDate(this.EXPIRE_TIME_IN_SECONDS);
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withClaim("name", user.getAccountEmail())
                .withClaim("role", user.getAccountRole())
                .withExpiresAt(expireDate)
                .sign(ALGORITHM);
    }

    private static Date createExpireDate(int seconds) {
        Instant instant = Instant.now().plusSeconds(seconds);
        return Date.from(instant);
    }

    public boolean hasJWTHeader(HttpHeaders headers) {
        List<String> header = headers.getRequestHeader("Authorization");

        if (header == null || header.size() < 1) {
            return false;
        }

        String JWT = header.get(0);
        if (!JWT.contains("Bearer ")) {
            return false;
        }

        return true;
    }

}
