package projeto.redes2.project.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	@NotBlank
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	@NotBlank
	@Column(length = 120, nullable = false)
	private String description;
	
	@NotNull
	@Column(length = 1, nullable = false) 
	private Boolean situation;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnoreProperties({hibernateLazyInitializer})
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Informando que o atributo pode ser escrito na desserialização mas não é lido na serialização.
	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;

}
