package com.bijlipay.security.hash;

//import com.bijlipay.model.common.PasswordToken;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {


    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    String AUTHORITIES_KEY = "authorities-key";

    @Value("${app.secret}")
    private String secret;

    //2.Read Claims
    public Claims getClaim(String token){

        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    //3.Read Exp date
    public Date getExpDate(String token){
        return getClaim(token).getExpiration();
    }




    //4.Read subject/username
    public String getUsername(String token){
        return getClaim(token).getSubject();
    }

    //5.Validate Exp Date
    public boolean isTokenExp(String token){
        Date expdate=getExpDate(token);
        return expdate.before(new Date(System.currentTimeMillis()));
    }

    //6.Validate username in token and database,expDate
    public boolean validateToken(String token,String username){
        String tokenUserName=getUsername(token);
        return (username.equals(tokenUserName) && !isTokenExp(token));
    }

    public boolean validateToken1(String authToken) {
        String tokenUserName=getUsername(authToken);
        return true;
    }




    //1.Generate Token
    public String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("Vaishu")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(15)))
                .signWith(SignatureAlgorithm.HS512,secret.getBytes())
                .compact();

    }

    public int getUserIdFromJWT(String token) {
        return Integer.parseInt((String)this.getClaimsBody(token, Claims::getSubject));
    }

    private <T> T getClaimsBody(String token, Function<Claims, T> clamis) {
        Claims claimsbody = this.getAllClaimsFromToken(token);
        return clamis.apply(claimsbody);
    }

    private Claims getAllClaimsFromToken(String token) {
        return (Claims)Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }























}



