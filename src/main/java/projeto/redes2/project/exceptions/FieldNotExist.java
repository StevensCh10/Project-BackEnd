package projeto.redes2.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FieldNotExist extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FieldNotExist(String message) {
		super(message);
	}
}
