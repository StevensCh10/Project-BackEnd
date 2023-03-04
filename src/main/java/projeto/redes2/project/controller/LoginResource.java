package projeto.redes2.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.redes2.project.model.User;
import projeto.redes2.project.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class LoginResource {
	
	private final UserService userService;
	
	public LoginResource(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/checkLogin/{userName}/{password}")
	public ResponseEntity<User> checkLogin(@PathVariable String userName, @PathVariable String password){
		User user = userService.checkLogin(userName, password);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User newUser = userService.register(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
}
