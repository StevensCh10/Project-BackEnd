package projeto.redes2.project;

import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import projeto.redes2.project.exception.EntityAlreadyExists;
import projeto.redes2.project.exception.EntityInUse;
import projeto.redes2.project.model.User;
import projeto.redes2.project.service.UserService;

@SpringBootTest
class UserTests {

	@Autowired
	UserService service;
	
	@Test
	void mustFail_whenUserWithoutNameIsRegistered() {
		assertThrows(ConstraintViolationException.class, () -> {
			User user = new User(null, null, 22, "test001@outlook.com", "testUsername001", "testPassword001");
			service.register(user);
		});
	}
	
	@Test
	void mustFail_whenRegisteredUserWithEmailInUse() {
		assertThrows(EntityAlreadyExists.class, () -> {
			User user = new User(null, "testName002", 22, "stevensch10@outlook.com", "testUsername002", "testPassword002");
			service.register(user);
		});
	}
	
	@Test
	void mustFailt_whemDeleteUserInUse() {
		assertThrows(EntityInUse.class, () -> {			
			service.delete(1L); //Id de um User no MEU DB
		});
	}
}
