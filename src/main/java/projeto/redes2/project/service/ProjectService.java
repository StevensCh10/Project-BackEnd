package projeto.redes2.project.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.redes2.project.exceptions.EntityAlreadyExists;
import projeto.redes2.project.exceptions.EntityInUse;
import projeto.redes2.project.exceptions.EntityNotFound;
import projeto.redes2.project.exceptions.EntityNotFoundInTheAppeal;
import projeto.redes2.project.model.Project;
import projeto.redes2.project.model.User;
import projeto.redes2.project.repository.ProjectRepository;
import projeto.redes2.project.repository.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public ArrayList<Project> all(Long userID){
		userRepository.findById(userID).orElseThrow(() -> new EntityNotFoundInTheAppeal(String.format("Usuário com id %d não está cadastrado!", userID)));
		return repository.allProjects(userID);
	}
	
	public Project find(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundInTheAppeal(String.format("Projeto com id %d não está cadastrado!", id)));
	}
	
	public Project add(Project p) {
		Long userID = p.getUser().getId();
			
		if(repository.findByName(p.getName()) == null) {		
			User user = userRepository.findById(userID).orElseThrow(() -> new EntityNotFound(String.format("Usuário com id %d não está cadastrado!", userID)));
			p.setUser(user);
			return repository.saveAndFlush(p);						
		}
		throw new EntityAlreadyExists(String.format("Já existe um projeto com nome \"%s\" !", p.getName()));		
	}
	
	public Project updatePartial(Map<String, Object> fields, Long id) {
		Project projectDestiny = find(id);
		
		ObjectMapper objMapper = new ObjectMapper();
		Project projectFields = objMapper.convertValue(fields, Project.class);
		
		fields.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(Project.class, propertyName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, projectFields);	
			ReflectionUtils.setField(field, projectDestiny, newValue);
		});	
		return repository.save(projectDestiny);		
	}
	
	public Project update(Project projectAtt, Long id) {
		Project currentProject = find(id);
		
		Long userID = projectAtt.getUser().getId();
		userRepository.findById(userID).orElseThrow(() -> new EntityNotFound(String.format("Usuário com id %d não está cadastrado!", userID)));
		
		BeanUtils.copyProperties(projectAtt, currentProject, "id");
		return repository.saveAndFlush(currentProject);
	}
	
	public void delete(Long id) {
		try {
			find(id);
			repository.deleteById(id);		
		}catch(DataIntegrityViolationException e) {
			throw new EntityInUse(String.format("Projeto com id %d não pode ser excluído, pois está em uso!", id));
		}
	}
	
	private void findUserById(long userID) {
		userRepository.findById(userID).orElseThrow(() -> new EntityNotFound(String.format("Usuário com id %d não está cadastrado!", userID)));
	}
}