package projeto.redes2.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projeto.redes2.project.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(
			value = "SELECT * FROM user WHERE user_name = :user_name AND password = :password",
			nativeQuery = true
	)
	public User checkLogin(@Param("user_name") String user_name, @Param("password") String password);
}
