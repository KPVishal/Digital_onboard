package com.bijlipay.security;

//import com.bijlipay.security.hash.JwtUtil;

import com.bijlipay.exception.UnAuthorizedException;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.security.hash.JwtUtil;
import com.bijlipay.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


   @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil util;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

  /*  @Autowired
    private CustomUserDetailsService customUserDetailsService;*/

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException
    {

        //read the token from auth header
        String token=request.getHeader("Authorization");
        if(token!=null){
            //do validation
            String username= util.getUsername(token);
            if (username!=null &&
                    SecurityContextHolder.getContext()
                            .getAuthentication()==null){
                UserDetails user=userDetailsService.loadUserByUsername(username);
                //validate token
                boolean isValid= util.validateToken(token,username);
                if(isValid){
                    UsernamePasswordAuthenticationToken authtoken
                            =new UsernamePasswordAuthenticationToken(username,user.getPassword(),
                            user.getAuthorities());
                    authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //final object stored
                    SecurityContextHolder.getContext().setAuthentication(authtoken);
                }
            }
        }
        chain.doFilter(request,response);


    }

    /*protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = this.getJwtFromRequest(request);
            if (util.validateToken1(jwt)) {
                int userId = this.util.getUserIdFromJWT(jwt);
                UserAuthentication usertoken = this.userAuthenticateService.getUser((long)userId);
                UsernamePasswordAuthenticationToken e;
                if (!jwt.equals(usertoken.getToken())) {
                    JwtAuthenticationEntryPoint jwtAuthPoint = new JwtAuthenticationEntryPoint();
                    e = null;

                    jwtAuthPoint.commence(request, response, e);
                    throw new UnAuthorizedException("can't login same user on same time!");
                }

                UserDetails userDetails = this.customUserDetailsService.loadUserById((long)userId);
                e = new UsernamePasswordAuthenticationToken(userDetails, SecurityContextHolder.getContext().getAuthentication(), userDetails.getAuthorities());
                e.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                System.out.println("Authenticated User " + e.getName());
                SecurityContextHolder.getContext().setAuthentication(e);
            }
        } catch (Exception var11) {
            logger.error("Could not set user authentication in security context", var11);
        }

        filterChain.doFilter(request, response);
    }*/

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.hasText(bearerToken) /*&& bearerToken.startsWith("Bearer ")*/ ? bearerToken.substring(7) : null;
    }
}
