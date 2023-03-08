package projeto.redes2.project.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.redes2.project.model.User;
import projeto.redes2.project.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserResource {
	
	private final UserService service;
	
	public UserResource(UserService userService) {
		this.service = userService;
	}	
	
	@PatchMapping("/{id}")
	public User updatePartial(@RequestBody Map<String, Object> fields, @PathVariable Long id){
		return service.updatePartial(fields, id);
	}
	
	@PutMapping("/{id}")
	public User update(@RequestBody User userAtt, @PathVariable Long id) {
		return service.update(userAtt, id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}