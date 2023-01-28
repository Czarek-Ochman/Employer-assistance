//package pl.employer.assistance.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.Key;
//import java.util.Base64;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Set;
//import java.security.Principal;
//
//public class JwtFilter extends BasicAuthenticationFilter {
//
//    public JwtFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader("Authorization");
//        UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {
//
//        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("thisisanicelongsecretkey12345678" .getBytes())
//                .parseClaimsJws(header.replace("Bearer ", ""));
//
//        String username = claimsJws.getBody().get("sub").toString();
//        String role = claimsJws.getBody().get("role").toString();
//        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
//        return usernamePasswordAuthenticationToken;
//    }
//}

package pl.employer.assistance.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.employer.assistance.service.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;


public class JwtFilter extends BasicAuthenticationFilter {
    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private AuthService authService;



    //    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/register") || request.getRequestURI().contains("/swagger") || request.getRequestURI().contains("/webjars") || request.getRequestURI().contains("/api-docs")
//                || request.getRequestURI().contains("/refresh")   || request.getRequestURI().contains("/control-panel/user")) {
//            chain.doFilter(request, response);
//            return;
//        }
//        try {
//            String header = request.getHeader("Authorization");
//            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
//            SecurityContextHolder.getContext().setAuthentication(authResult);
//            chain.doFilter(request, response);
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/register") || request.getRequestURI().contains("/swagger") || request.getRequestURI().contains("/webjars") || request.getRequestURI().contains("/api-docs")
                || request.getRequestURI().contains("/refresh")   || request.getRequestURI().contains("/control-panel/user")) {
            chain.doFilter(request, response);
            return;
        }
            String header = request.getHeader("Authorization");
            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
            String token = header.substring(7);
            if (authService.isValidToken(token)) {
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }

    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("2dae84f846e4f4b158a8d26681707f4338495bc7ab68151d7f7679cc5e56202dd3da0d356da007a7c28cb0b780418f4f3246769972d6feaa8f610c7d1e7ecf6a")
                .parseClaimsJws(header.replace("Bearer ", ""));

        String username = claimsJws.getBody().get("sub").toString();
        String role = claimsJws.getBody().get("role").toString();
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
        return usernamePasswordAuthenticationToken;
    }
}

