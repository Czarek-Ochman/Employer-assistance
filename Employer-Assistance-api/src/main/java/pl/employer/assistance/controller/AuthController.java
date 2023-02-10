package pl.employer.assistance.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.employer.assistance.model.dto.UserDto;
import pl.employer.assistance.service.AuthService;
import pl.employer.assistance.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto){
        Map<String, String> tokens = authService.login(userDto);
        if(tokens.containsKey("access_token")) {
            return new ResponseEntity<>(tokens, HttpStatus.OK);
        }
        return new ResponseEntity<>(tokens, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/refresh/{refreshToken}")
    public ResponseEntity<Map<String, String>> refreshAccessToken(@PathVariable String refreshToken) {
        Map<String, String> tokens = authService.refreshAccessToken(refreshToken);
        if(tokens.containsKey("access_token")) {
            return new ResponseEntity<>(tokens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tokens, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto user){
        return ResponseEntity.ok(authService.registerNewUserAccount(user));
    }
}
