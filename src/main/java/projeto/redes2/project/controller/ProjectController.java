package projeto.redes2.project.controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import projeto.redes2.project.model.Project;
import projeto.redes2.project.service.ProjectService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/project")
public class ProjectController {
	
	private final ProjectService service;
	
	public ProjectController(ProjectService projectService) {
		this.service = projectService;
	}
	
	@GetMapping("/{id}") 
	public Project get(@Valid @PathVariable Long id){
		return service.find(id);			
	}
	
	@GetMapping("/userProjects/{idUser}")
	public ResponseEntity<ArrayList<Project>> allProjects(@Valid @PathVariable Long idUser){
		ArrayList<Project> projects = service.all(idUser);
		return ResponseEntity.ok(projects);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Project add(@Valid @RequestBody Project receivedProject){	
		return service.add(receivedProject);	
	}
	
	@PatchMapping("/{id}")
	public Project updatePartial(@Valid @RequestBody Map<String, Object> fields, @Valid @PathVariable Long id, @Valid HttpServletRequest request){
		return service.updatePartial(fields, id, request);
	}
	
	@PutMapping("/{id}")
	public Project update(@Valid @RequestBody Project projectAtt, @Valid @PathVariable Long id){
		return service.update(projectAtt, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @PathVariable Long id){
		service.delete(id);	
	} 
}