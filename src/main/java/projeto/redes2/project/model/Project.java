package projeto.redes2.project.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data      //(já vem com @Getter, @Setter, @EqualsAndHashCode e @ToString
@Entity
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	@Column(length = 120, nullable = false)
	private String description;
	
	@Column(length = 1, nullable = false) 
	private Boolean situation;
	
	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;

}
