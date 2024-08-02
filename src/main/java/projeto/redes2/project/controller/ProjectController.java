package projeto.redes2.project.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import projeto.redes2.project.dto.ProjectDTO;
import projeto.redes2.project.model.Project;
import projeto.redes2.project.service.ProjectService;
import projeto.redes2.project.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectService service;
	private final UserService userService;
	
	@GetMapping("/{id}") 
	@ResponseStatus(HttpStatus.OK)
	public ProjectDTO get(@PathVariable Long id){
		return service.getProject(id);			
	}
	
	@GetMapping("/userProjects/{idUser}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProjectDTO> allProjects(@PathVariable Long idUser){
		return service.all(idUser);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDTO add(@Valid @RequestBody Project receivedProject){
		var user = userService.find(receivedProject.getUser().getId());
		return service.add(receivedProject, user);	
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectDTO updatePartial(@Valid @RequestBody Map<String, Object> fields, @PathVariable Long id, @Valid HttpServletRequest request){
		return service.updatePartial(fields, id, request);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectDTO update(@Valid @RequestBody Project projectAtt, @PathVariable Long id){
		return service.update(projectAtt, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		service.delete(id);	
	} 
}