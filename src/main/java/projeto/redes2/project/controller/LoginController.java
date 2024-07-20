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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import projeto.redes2.project.model.User;
import projeto.redes2.project.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
	
	private final UserService userService;
	
	@GetMapping("/{userName}/{password}")
	public User checkLogin(@Valid @PathVariable String userName, @Valid @PathVariable String password){
		return userService.checkLogin(userName, password);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public User registerUser(@Valid @RequestBody User user){
		return userService.register(user);
	}
}