package projeto.redes2.project.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}