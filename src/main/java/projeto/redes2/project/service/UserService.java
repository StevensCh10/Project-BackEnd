package projeto.redes2.project.service;

import org.springframework.stereotype.Service;
import projeto.redes2.project.exceptions.EntityNotFoundInTheAppeal;
import projeto.redes2.project.model.User;
import projeto.redes2.project.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repository;

	public UserService(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
	public User find(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundInTheAppeal(String.format("usuário com id %d não está cadastrado!", id)));
	}

	public User checkLogin(String userName, String password) {
		return repository.checkLogin(userName, password);
	}
		
	public User register(User u) {
		return repository.save(u);
	}
	
	public User updateUserName(String newUserName, Long id) {
		User byId = repository.getReferenceById(id);
		byId.setUserName(newUserName);
		
		return repository.saveAndFlush(byId);
	}
	
	public User updatePassword(String password, Long id) {
		User byId = repository.getReferenceById(id);
		byId.setPassword(password);
		
		return repository.saveAndFlush(byId);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
