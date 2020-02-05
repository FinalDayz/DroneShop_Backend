package com.github.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.modal.Account;

import java.time.Instant;
import java.util.Date;

public class JWTUtils {
    public static final String SECRET = "95b9d3abab1332023a58b819bb7d0b317e2899a8";
    public static final String ISSUER = "droneshop";
    public static final int EXPIRE_SECONDS = 60 * 60 * 24;

    public static DecodedJWT decodeJWT(String JWTString) {
        return JWT.decode(JWTString);
    }

    public static boolean isValid(String JWTString) {
        Algorithm algorithm = Algorithm.HMAC256(JWTUtils.SECRET);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(JWTUtils.ISSUER).build();
        try {
            verifier.verify(JWTString);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public static String creatJWT(Account accountReturnes) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Date expireDate = createExpireDate(EXPIRE_SECONDS);

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withClaim("name", accountReturnes.getAccountEmail())
                .withClaim("role", accountReturnes.getAccountRole())
                .withExpiresAt(expireDate)
                .sign(algorithm);
        }

        private static Date createExpireDate(int seconds) {
            Instant instant = Instant.now().plusSeconds(seconds);
            return Date.from(instant);
        }
}
