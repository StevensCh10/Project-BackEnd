package projeto.redes2.project.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.redes2.project.core.validation.Age;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)  
	private Long id;
	
	@NotBlank
	@Column(length = 50, nullable = false, unique=true)
	private String name;
	
	@NotNull
	//@DecimalMin("18")
	@Age
	@Column(nullable = false)
	private int age;
	
	@Email
	@NotBlank
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	private String username;
	
	@NotBlank
	@Column(length = 50, nullable = false)
	private String password;
	
	private String role;
}