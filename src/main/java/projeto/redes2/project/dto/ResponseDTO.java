package projeto.redes2.project.dto;

import projeto.redes2.project.model.User;

public record ResponseDTO (User user, String token) { }