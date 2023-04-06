package projeto.redes2.project.services;

import java.lang.reflect.Field;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.redes2.project.exceptions.EntityAlreadyExists;
import projeto.redes2.project.exceptions.EntityInUse;
import projeto.redes2.project.exceptions.EntityNotFoundInTheAppeal;
import projeto.redes2.project.models.User;
import projeto.redes2.project.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public User find(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundInTheAppeal(String.format("User '%s' not unregistered.", id)));
	}

	public User checkLogin(String userName, String password) {
		User user = repository.checkLogin(userName, password);
		
		if(user == null) {
			if(repository.findByUsername(userName) == null) {
				throw new EntityNotFoundInTheAppeal(String.format("User '%s' not unregistered.", userName));
			}
			throw new EntityNotFoundInTheAppeal("Incorret password.");
		}
		return user;
	}
		
	public User register(User user) {
		if(repository.findByName(user.getName()) == null) {
			if(repository.findByEmail(user.getEmail()) != null) {
				throw new EntityAlreadyExists(String.format("Email '%s' is already registered.", user.getEmail()));
			}
			return repository.save(user);			
		}
		throw new EntityAlreadyExists(String.format("Name '%s' unavailable.", user.getName()));
	}
	
	public User updatePartial(Map<String, Object> fields, Long id) {
		User userDestiny = find(id);
		
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
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
		System.out.println("OPA");
		try {
			find(id);
			repository.deleteById(id);		
		}catch(DataIntegrityViolationException e) {
			throw new EntityInUse(String.format("User with id %d cannot be deleted as it is in use.", id));
		}
	}
}