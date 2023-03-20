package projeto.redes2.project.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@Column(length = 50, nullable = false, unique=true)
	private String name;
	
	@Column(nullable = false)
	private int age;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 50, nullable = false)
	private String username;
	
	@Column(length = 50, nullable = false)
	private String password;

}
