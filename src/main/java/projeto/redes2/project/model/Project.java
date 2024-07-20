package projeto.redes2.project.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.redes2.project.core.validation.Groups;

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
	@JsonIgnoreProperties(value = {"name", "age", "email", "username", "password"}, allowGetters = true)
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.GroupUserId.class)
	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Informando que o atributo pode ser escrito na desserialização mas não é lido na serialização.
	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;
}
