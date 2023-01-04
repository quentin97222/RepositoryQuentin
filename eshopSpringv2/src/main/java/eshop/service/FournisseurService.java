package eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eshop.entity.Adresse;
import eshop.entity.Fournisseur;
import eshop.exception.FournisseurException;
import eshop.repository.FournisseurRepository;


@Service
public class FournisseurService {

	@Autowired
	private FournisseurRepository fournisseurRepo;

	
	public void create(Fournisseur fournisseur) {
		checkFournisseurIsNotNull(fournisseur);
		if (fournisseur.getNom() == null || fournisseur.getNom().isEmpty()) {
			throw new FournisseurException("nom vide");
		}
		fournisseurRepo.save(fournisseur);
	}

	private void checkFournisseurIsNotNull(Fournisseur fournisseur) {
		if (fournisseur == null) {
			throw new FournisseurException("formateur null");
		}		
	}
	
	public Fournisseur getById(Long id) {
		if (id == null) {
			throw new eshop.exception.IdException();
		}
		return fournisseurRepo.findByIdFetchProduits(id).orElseThrow(() -> {
			throw new FournisseurException("fournisseur inconnu");
		});
	}
	
	public List<Fournisseur> getAll() {
		return fournisseurRepo.findAll();
	}

	public Page<Fournisseur> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new FournisseurException();
		}
		return fournisseurRepo.findAll(pageable);
	}

	public Page<Fournisseur> getNextPage(Page<Fournisseur> page) {
		if (page == null) {
			throw new FournisseurException();
		}
		return fournisseurRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Fournisseur> getPrevious(Page<Fournisseur> page) {
		if (page == null) {
			throw new FournisseurException();
		}
		return fournisseurRepo.findAll(page.previousOrFirstPageable());
	}
	
	public void delete(Fournisseur fournisseur) {
		checkFournisseurIsNotNull(fournisseur);
		deleteById(fournisseur.getId());
	}

	public void delete(Long id) {
		deleteById(id);
	}
	
	private void deleteById(Long id) {
		Fournisseur fournisseur = getById(id);
		fournisseurRepo.delete(fournisseur);
	}
	
	public Fournisseur update(Fournisseur fournisseur) {
		// @formatter:off
		Fournisseur fournisseurEnBase = getById(fournisseur.getId());
		fournisseurEnBase.setNom(fournisseur.getNom() != null ? fournisseur.getNom() : fournisseurEnBase.getNom());
		fournisseurEnBase.setEmail(fournisseur.getEmail());
		if (fournisseur.getAdresse() != null) {
			fournisseurEnBase.setAdresse(
								new Adresse(
										fournisseur.getAdresse().getNumero(), 
										fournisseur.getAdresse().getRue(),
										fournisseur.getAdresse().getCodePostal(),
										fournisseur.getAdresse().getVille()));
		} else {
			fournisseurEnBase.setAdresse(null);
		}
		fournisseurEnBase.setContact(fournisseur.getContact());
		fournisseurEnBase.setProduits(fournisseur.getProduits());
		return fournisseurRepo.save(fournisseurEnBase);
		// @formatter:on
	}
	
}
