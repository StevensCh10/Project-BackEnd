package projeto.redes2.project.exceptionhandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorHandler {
	
	private LocalDateTime timestamp;
	private String message;
}
