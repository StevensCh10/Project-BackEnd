package projeto.redes2.project.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.redes2.project.security.TokenService;
import lombok.RequiredArgsConstructor;
import projeto.redes2.project.dto.ResponseDTO;
import projeto.redes2.project.exception.EntityNotFoundInTheAppeal;
import projeto.redes2.project.model.User;
import projeto.redes2.project.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseDTO authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return invalidPassword(password, user);
        }
        throw new EntityNotFoundInTheAppeal("Email not found");
    }

    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return user;
        }
        return null;
    }

    private ResponseDTO invalidPassword(String password, User user){
        if(passwordEncoder.matches(password, user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return new ResponseDTO(user, token);
        }else{
            throw new EntityNotFoundInTheAppeal("Invalid password");
        }
    }
}