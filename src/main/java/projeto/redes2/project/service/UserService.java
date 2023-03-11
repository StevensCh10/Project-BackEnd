package projeto.redes2.project.service;

import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.redes2.project.exceptions.EntityAlreadyExists;
import projeto.redes2.project.exceptions.EntityInUse;
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
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundInTheAppeal(String.format("User '%s' not unregistered.", id)));
	}

	public User checkLogin(String userName, String password) {
		User user = repository.checkLogin(userName, password);
		
		if(user == null) {
			if(repository.findByUserName(userName) == null) {
				throw new EntityNotFoundInTheAppeal(String.format("User '%s' not unregistered.", userName));
			}
			throw new EntityNotFoundInTheAppeal("Incorret password.");
		}
		return user;
	}
		
	public User register(User user) {
		System.out.println(repository.findByName(user.getName()));
		if(repository.findByName(user.getName()) == null) {
			return repository.save(user);			
		}
		throw new EntityAlreadyExists(String.format("Name '%s' entered unavailable.", user.getName()));
	}
	
	public User updatePartial(Map<String, Object> fields, Long id) {
		User userDestiny = find(id);
		
		ObjectMapper objMapper = new ObjectMapper();
		User userFields = objMapper.convertValue(fields, User.class);
		
		fields.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(User.class, propertyName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, userFields);	
			ReflectionUtils.setField(field, userDestiny, newValue);
		});	
		return repository.save(userDestiny);		
	}
	
	public User update(User userAtt, Long id) {
		User currentUser = find(id);
		User find = repository.findByName(userAtt.getName());
		
		if(find != null && find.getId() != id) {
			throw new EntityAlreadyExists(String.format("name '%s' unavailable", userAtt.getName()));
		}
		BeanUtils.copyProperties(userAtt, currentUser, "id");
		return repository.saveAndFlush(currentUser);
	}

	public void delete(Long id) {
		try {
			find(id);
			repository.deleteById(id);		
		}catch(DataIntegrityViolationException e) {
			throw new EntityInUse(String.format("Project with id %d cannot be deleted as it is in use.", id));
		}
	}
}
