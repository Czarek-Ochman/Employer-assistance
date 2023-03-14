package pl.employer.assistance.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.employer.assistance.model.mapper.UserMapper;
import pl.employer.assistance.repository.UserRepository;
import pl.employer.assistance.service.exception.ResourceNotFoundException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@Service
public class AuthService {

    private final UserService userService;
    BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private long JWT_EXPIRATION_TIME = 86400000;

    final String SECRET = "2dae84f846e4f4b158a8d26681707f4338495bc7ab68151d7f7679cc5e56202dd3da0d356da007a7c28cb0b780418f4f3246769972d6feaa8f610c7d1e7ecf6a";
    final Date now = new Date(), validity = new Date(now.getTime() + 86400000);


    public AuthService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Map<String, String> login(UserDto userDto) {
        Map<String, String> tokens = new HashMap<>();
        if (userService.existsByEmail(userDto.getEmail())) {
            User user = userService.getUserByEmail(userDto.getEmail());
            if (b.matches(userDto.getPassword(), user.getPassword())) {
                String accessToken = getAccessToken(user);
                String refreshToken = generateRefreshToken(user);
                userService.setAccessToken(user.getId(), accessToken);
                userService.setRefreshToken(user.getId(), refreshToken);
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                return tokens;
            } else {
                return tokens;
            }
        } else {
            return tokens;
        }
    }

    private String getAccessToken(User user) {
        UUID uuid = UUID.randomUUID();
        String accessToken = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("role", "USER")
                .claim("iat", uuid.toString())
                .setIssuedAt(now)
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return accessToken;
    }


    public String generateRefreshToken(User user) {
        UUID uuid = UUID.randomUUID();
        String refreshToken = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", "REFRESH")
                .claim("iat", uuid.toString())
                .setIssuedAt(now)
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        userService.setRefreshToken(user.getId(), refreshToken);
        return refreshToken;
    }

    public boolean isValidToken(String accessToken) {
        User user = userRepository.getUserByAccessToken(accessToken);
        if (user == null) {
            if (!user.getAccessToken().equals(accessToken)) {
                return false;
            }
            return false;
        }else {
            return true;
        }
    }

    public UserDto registerNewUserAccount(UserDto userDto) {
        if (userService.existsByEmail(userDto.getEmail())) {
            throw new ResourceNotFoundException(
                    "There is an account with that email adress:" + userDto.getEmail());
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userService.addUser(userDto);
    }

    public Map<String, String> refreshAccessToken(String refreshToken) {
        Map<String, String> tokens = new HashMap<>();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(refreshToken)
                    .getBody();

            String email = claims.getSubject();
            User user = userService.getUserByEmail(email);

            if (user != null) {
                if (refreshToken.equals(user.getRefreshToken())) {
                    String accessToken = getAccessToken(user);
                    refreshToken = generateRefreshToken(user);
                    userService.setRefreshToken(user.getId(), refreshToken);
                    tokens.put("access_token", accessToken);
                    tokens.put("refresh_token", refreshToken);
                } else {
                    tokens.put("error", "Invalid refresh token");
                }
            } else {
                tokens.put("error", "Invalid refresh token");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tokens;
    }

}