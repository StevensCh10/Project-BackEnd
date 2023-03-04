package projeto.redes2.project.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import projeto.redes2.project.exceptions.EntityNotFound;
import projeto.redes2.project.exceptions.EntityNotFoundInTheAppeal;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler()
	public ResponseEntity<?> handleEntityNotFoundInTheAppeal(EntityNotFoundInTheAppeal e, WebRequest request){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler()
	public ResponseEntity<?> handleEntityNotFound(EntityNotFound e, WebRequest request){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	//FALTA AS OUTRAS
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = new ErrorHandler(LocalDateTime.now(), status.getReasonPhrase());			
		}else if(body instanceof String) {
			body = new ErrorHandler(LocalDateTime.now(), (String) body);
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
