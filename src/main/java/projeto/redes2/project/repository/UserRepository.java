package projeto.redes2.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.redes2.project.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(
			value = "SELECT * FROM user WHERE username = :username AND password = :password",
			nativeQuery = true
	)
	public User checkLogin(@Param("username") String username, @Param("password") String password);
	
	public User findByUsername(String username);
	
	@Query(
			value = "SELECT * FROM user WHERE name =:name",
			nativeQuery=true
	)
	public User findByName(@Param("name") String name);
}
