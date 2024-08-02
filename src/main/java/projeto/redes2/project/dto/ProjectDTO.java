package projeto.redes2.project.dto;

import projeto.redes2.project.model.Project;
import projeto.redes2.project.model.User;

public record ProjectDTO(Long id, String name, String description, Boolean situation, User user) {
    public static ProjectDTO fromEntity(Project project) {
        return new ProjectDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getSituation(),
            project.getUser()
        );
    }
}
