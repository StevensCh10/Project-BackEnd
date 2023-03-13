package projeto.redes2.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/{userName}/{password}")
	public User checkLogin(@PathVariable String userName, @PathVariable String password){
		return userService.checkLogin(userName, password);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public User registerUser(@RequestBody User user){
		return userService.register(user);
	}
}
