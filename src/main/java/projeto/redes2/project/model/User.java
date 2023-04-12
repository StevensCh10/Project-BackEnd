package projeto.redes2.project.model;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.redes2.project.core.validation.Age;
import projeto.redes2.project.core.validation.Groups;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(groups = Groups.GroupUserId.class)
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
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}