package projeto.redes2.project;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projeto.redes2.project.exceptions.EntityNotFoundInTheAppeal;
import projeto.redes2.project.services.ProjectService;

@SpringBootTest
public class ProjectTests {
	
	@Autowired
	ProjectService service;
	
	@Test
	void mustFail_whenDeleteProjectInUse() {
		assertThrows(EntityNotFoundInTheAppeal.class, () -> {
			service.delete(100L);
		});
	}
}
