package projeto.redes2.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUse extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntityInUse(String message) {
		super(message);
	}
}