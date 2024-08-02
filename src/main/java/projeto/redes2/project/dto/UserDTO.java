package projeto.redes2.project.dto;

import projeto.redes2.project.model.User;

public record UserDTO(Long id, String name, int age, String email, String username) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getAge(),
            user.getEmail(),
            user.getUsername()
        );
    }
} 
