package sbp.demo.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import sbp.demo.configs.security.JwtConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtTokenUtil {

//    JwtTokenUtil() throws Exception {
//        throw new Exception("JwtTokenUtil is a utility class");
//    }

    @Autowired
    private static JwtConfig jwtConfig;

    public static void setJwtConfig(JwtConfig jc) {
        jwtConfig = jc;
    }

    public static String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getTokenValidity() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    public static Boolean validate(String token) {
        Claims claims = getClaimsFromToken(token);
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return !isTokenExpired;
    }

    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
