package tn.esprit.rh.achat.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.FournisseurDTO;
import tn.esprit.rh.achat.services.IFournisseurService;

import java.util.List;


@RestController
@RequestMapping("/fournisseur")
public class FournisseurRestController {

	@Autowired
	IFournisseurService fournisseurService;

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-all-fournisseurs
	@GetMapping("/retrieve-all-fournisseurs")
	@ResponseBody
	public List<Fournisseur> getFournisseurs() {
		return fournisseurService.retrieveAllFournisseurs();
	}

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-fournisseur/8
	@GetMapping("/retrieve-fournisseur/{fournisseur-id}")
	@ResponseBody
	public Fournisseur retrieveFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		return fournisseurService.retrieveFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/add-fournisseur
	@PostMapping("/add-fournisseur")
	@ResponseBody
	public ResponseEntity<Fournisseur> addFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
		return new ResponseEntity<>(fournisseurService.addFournisseur(fournisseurDTO), HttpStatus.CREATED);
	}

	// http://localhost:8089/SpringMVC/fournisseur/remove-fournisseur/{fournisseur-id}
	@DeleteMapping("/remove-fournisseur/{fournisseur-id}")
	@ResponseBody
	public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		fournisseurService.deleteFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/modify-fournisseur
	@PutMapping("/modify-fournisseur")
	@ResponseBody
	public Fournisseur modifyFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
		return fournisseurService.updateFournisseur(fournisseurDTO);
	}

}
