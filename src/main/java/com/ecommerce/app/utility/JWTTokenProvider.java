/*package com.ecommerce.app.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ecommerce.app.entity.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.ecommerce.app.constant.SecurityConstant.*;

@Component
public class JWTTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserPrincipal userPrincipal;

    public String generateJwtToken(UserPrincipal userPrincipal) {
        return JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION)
                .withSubject(userPrincipal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() - EXPIRATION_TIME))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public Authentication getAuthentication(String username , HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username , request);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    public boolean isTokenValid(String username , String token) {
        JWTVerifier jwtVerifier = getJwtVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(jwtVerifier , token);
    }

    private boolean isTokenExpired(JWTVerifier jwtVerifier , String token) {
        Date expired = jwtVerifier.verify(token).getExpiresAt();
        return expired.before(new Date());
    }

    public String getSubject(String token) {
        JWTVerifier jwtVerifier = getJwtVerifier();
        return jwtVerifier.verify(token).getSubject();
    }

    private JWTVerifier getJwtVerifier() {
        JWTVerifier jwtVerifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            jwtVerifier = JWT.require(algorithm).withIssuer(GET_ARRAYS_LLC).build();
        }catch (JWTVerificationException e) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return jwtVerifier;
    }

}

 */

