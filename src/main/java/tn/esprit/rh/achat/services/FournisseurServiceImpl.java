package tn.esprit.rh.achat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.FournisseurDTO;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

import java.util.List;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		return fournisseurRepository.findAll();
	}


	public Fournisseur addFournisseur(FournisseurDTO fournisseurDTO) {
		return fournisseurRepository.save(new Fournisseur(fournisseurDTO.getIdFournisseur(),fournisseurDTO.getCode(),fournisseurDTO.getLibelle()));

	}

	public Fournisseur updateFournisseur(FournisseurDTO fournisseurDTO) {
		return fournisseurRepository.save(new Fournisseur(fournisseurDTO.getIdFournisseur(),fournisseurDTO.getCode(),fournisseurDTO.getLibelle()));

	}

	@Override
	public void deleteFournisseur(Long fournisseurId) {
		fournisseurRepository.deleteById(fournisseurId);

	}

	@Override
	public Fournisseur retrieveFournisseur(Long fournisseurId) {

		return fournisseurRepository.findById(fournisseurId).orElse(null);
	}

	

}