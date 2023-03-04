package projeto.redes2.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User receivedUser){
		User user = service.register(receivedUser);
		//return new ResponseEntity<>(user, HttpStatus.OK);
		return ResponseEntity.ok(user);
	}
	
	
	@PutMapping("/updateUserName/{newUserName}/{id}")
	public ResponseEntity<User> updateUserName(@PathVariable String newUserName, @PathVariable Long id){
		User user = service.updateUserName(newUserName, id);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/updatePassword/{newPassword}/{id}")
	public ResponseEntity<User> updatePassword(@PathVariable String newPassword, @PathVariable Long id){
		User user = service.updatePassword(newPassword, id);
		return ResponseEntity.ok(user);
	}

}