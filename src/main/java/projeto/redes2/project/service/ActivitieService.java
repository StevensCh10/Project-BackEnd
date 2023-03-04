package projeto.redes2.project.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import projeto.redes2.project.model.Activitie;
import projeto.redes2.project.repository.ActivitieRepository;

@Service
public class ActivitieService {
	
	private final ActivitieRepository activitieRepository;
	
	public ActivitieService(ActivitieRepository activitieRepository) {
		this.activitieRepository = activitieRepository;
	}
	
	
	
	public ArrayList<String> allActivities(Long id){
		return activitieRepository.allActivities(id);
	}
	
	public Activitie addActivitie(Activitie a, Long idProject) {
		Activitie activitie = activitieRepository.saveAndFlush(a);
		activitieRepository.updateFkProject(idProject, activitie.getId());
		return activitie;
	}
	
	public Activitie updateDescription(String newDescription, Long id) {
		Activitie byId = activitieRepository.getReferenceById(id);
		byId.setDescription(newDescription);
		
		return activitieRepository.saveAndFlush(byId);
	}
	
	public void deleteActivitie(Long id) {
		activitieRepository.deleteById(id);
	}
}
