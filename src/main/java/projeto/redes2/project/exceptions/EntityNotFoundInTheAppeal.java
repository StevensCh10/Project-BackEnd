package projeto.redes2.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundInTheAppeal extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundInTheAppeal(String message) {
		super(message);
	}
}