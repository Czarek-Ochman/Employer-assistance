package pl.employer.assistance.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.employer.assistance.model.dto.UserDto;
import pl.employer.assistance.service.AuthService;
import pl.employer.assistance.service.UserService;

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
    public String login(@RequestBody UserDto userDto) {
        return authService.login(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto user){
        return ResponseEntity.ok(userService.addUser(user));
    }
}
