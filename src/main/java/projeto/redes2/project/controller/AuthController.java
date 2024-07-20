package projeto.redes2.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import projeto.redes2.project.dto.ResponseDTO;
import projeto.redes2.project.model.LoginRequest;
import projeto.redes2.project.model.User;
import projeto.redes2.project.security.TokenService;
import projeto.redes2.project.service.AuthService;
import projeto.redes2.project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseDTO response = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return response;
    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO validateToken(@Valid @RequestBody String token) {
        String email = tokenService.validateToken(token);
        if (email != null) {
            var user = authService.findByEmail(email);
            return new ResponseDTO(user, token);
        } else {
            return null;
        }
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO registerDonor(@Valid @RequestBody User user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userService.register(user);
            String token = this.tokenService.generateToken(newUser);
            return new ResponseDTO(newUser, token);
    }
}