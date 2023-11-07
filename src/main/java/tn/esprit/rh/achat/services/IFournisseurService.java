package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.FournisseurDTO;

import java.util.List;

public interface IFournisseurService {

	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(FournisseurDTO fournisseurDTO);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(FournisseurDTO fournisseurDTO);

	Fournisseur retrieveFournisseur(Long id);

}
