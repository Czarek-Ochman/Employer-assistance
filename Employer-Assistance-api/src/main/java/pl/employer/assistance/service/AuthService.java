package pl.employer.assistance.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.UserDto;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthService {

    private final UserService userService;

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

//          decode encode
//           if(user.getPassword() == userDto.getPassword()){
               long currentDate = System.currentTimeMillis();
               return Jwts.builder()
                       .setSubject(userDto.getEmail())
                       .signWith(SignatureAlgorithm.HS256, secretKeyEncoded)
                       .claim("role", "USER")
                       .setIssuedAt(now)
                       .setExpiration(validity)
                       .compact();
//           }else {
//               return "Wrong password!";
//           }
        }else {
            return "User with email" + userDto.getEmail() + "does not exist!";
        }
    }

}
