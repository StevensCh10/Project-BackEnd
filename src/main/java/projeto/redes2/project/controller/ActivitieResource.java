package projeto.redes2.project.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.redes2.project.model.Activitie;
import projeto.redes2.project.service.ActivitieService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/activitie")
public class ActivitieResource {
	
	private final ActivitieService service;
	
	public ActivitieResource(ActivitieService activiteService) {
		this.service = activiteService;
	}
	
	@GetMapping("/userActivities/{idUser}")
	public ResponseEntity<ArrayList<String>> allActivities(@PathVariable Long idUser){
		ArrayList<String> activities = service.allActivities(idUser);
		return new ResponseEntity<>(activities, HttpStatus.OK);
	}
	
	@PostMapping("/addActivitie/{idProject}")
	public ResponseEntity<Activitie> addActivitie(@RequestBody Activitie receivedActivitite,@PathVariable Long idProject){
		Activitie activitie = service.addActivitie(receivedActivitite, idProject);
		return new ResponseEntity<>(activitie, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateDescription/{newDescription}/{id}")
	public ResponseEntity<Activitie> updateDescription(@PathVariable String newDescription,@PathVariable Long id) {
		Activitie activitie = service.updateDescription(newDescription, id);
		return new ResponseEntity<>(activitie, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteActivitie/{id}")
	public ResponseEntity<Activitie> deleteActivitie(@PathVariable Long id){
		service.deleteActivitie(id);
		return ResponseEntity.noContent().build();
	}
}
