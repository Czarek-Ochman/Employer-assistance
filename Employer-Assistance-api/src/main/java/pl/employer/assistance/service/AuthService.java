package pl.employer.assistance.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.employer.assistance.service.exception.ResourceNotFoundException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthService {

    private final UserService userService;
    BCryptPasswordEncoder b = new BCryptPasswordEncoder();

    @Autowired
    private PasswordEncoder passwordEncoder;

    final String secretKey = "thisisanicelongsecretkey12345678", // Do przeniesienia
            secretKeyEncoded = Base64.getEncoder().encodeToString(secretKey.getBytes());
    final Date now = new Date(), validity = new Date(now.getTime()+1800000);

    Key key = new SecretKeySpec(
            secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName()); 


    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String login(UserDto userDto){

        if(userService.existsByEmail(userDto.getEmail())) {
          User user =  userService.getUserByEmail(userDto.getEmail());
            if(b.matches(userDto.getPassword(), user.getPassword())){
               long currentDate = System.currentTimeMillis();
               return Jwts.builder()
                       .setSubject(userDto.getEmail())
                       .signWith(SignatureAlgorithm.HS256, secretKeyEncoded)
                       .claim("role", "USER")
                       .setIssuedAt(now)
                       .setExpiration(validity)
                       .compact();
           }else {
               return "Wrong password!";
           }
        }else {
            return "User with email" + userDto.getEmail() + "does not exist!";
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
}
