package projeto.redes2.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import projeto.redes2.project.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	@Query(
		value = "SELECT * FROM project WHERE fk_user = :idUser",
		nativeQuery = true
	)
	public ArrayList<Project> allProjects(@Param("idUser") Long idUser);
	
	
	public Project findByName(@Param("name") String name);
	
	@Query(
			value = "SELECT description_activitie FROM activitie WHERE fk_project = :idProject",
			nativeQuery = true
		)
		public ArrayList<String> allActivities(@Param("idProject") Long idProject);
	
	@Transactional
	@Modifying
	@Query(
		value = "UPDATE project SET fk_user = :idUser WHERE id = :idProject",
		nativeQuery = true
	)
	public void updateFkUser(@Param("idUser") Long idUser,@Param("idProject") Long idProject);

}
