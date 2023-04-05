package projeto.redes2.project.controllers;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import projeto.redes2.project.models.Project;
import projeto.redes2.project.services.ProjectService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/project")
public class ProjectResource {
	
	private final ProjectService service;
	
	public ProjectResource(ProjectService projectService) {
		this.service = projectService;
	}
	
	@GetMapping("/{id}") 
	public Project get(@PathVariable Long id){
		return service.find(id);			
	}
	
	@GetMapping("/userProjects/{idUser}")
	public ResponseEntity<ArrayList<Project>> allProjects(@PathVariable Long idUser){
		ArrayList<Project> projects = service.all(idUser);
		return ResponseEntity.ok(projects);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Project add(@RequestBody @Valid Project receivedProject){	
		return service.add(receivedProject);	
	}
	
	@PatchMapping("/{id}")
	public Project updatePartial(@RequestBody Map<String, Object> fields, @PathVariable Long id, HttpServletRequest request){
		return service.updatePartial(fields, id, request);
	}
	
	@PutMapping("/{id}")
	public Project update(@RequestBody @Valid Project projectAtt, @PathVariable Long id){
		return service.update(projectAtt, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		service.delete(id);	
	} 
}