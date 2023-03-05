package projeto.redes2.project.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projeto.redes2.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	@Query(
		value = "SELECT * FROM project WHERE fk_user = :idUser",
		nativeQuery = true
	)
	public ArrayList<Project> allProjects(@Param("idUser") Long idUser);
	
	@Query(
			value = "SELECT * FROM project WHERE name = :name AND fk_user = :idUser",
			nativeQuery = true
	)
	public Project findByName(@Param("name") String name, @Param("idUser") Long idUser);
	
	/*
	@Transactional
	@Modifying
	@Query(
		value = "UPDATE project SET fk_user = :idUser WHERE id = :idProject",
		nativeQuery = true
	)
	public void updateFkUser(@Param("idUser") Long idUser,@Param("idProject") Long idProject);
	*/
}
