package eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eshop.entity.Produit;
import eshop.exception.IdException;
import eshop.exception.ProduitException;
import eshop.repository.ProduitRepository;

@Service
public class ProduitService {

	@Autowired
	private ProduitRepository produitRepo;

	private void checkProduitIsNotNull(Produit produit) {
		if (produit == null) {
			throw new ProduitException();
		}
	}

	public void create(Produit produit) {
		checkProduitIsNotNull(produit);
		if (produit.getLibelle() == null || produit.getLibelle().isEmpty()) {
			throw new ProduitException();
		}
		produitRepo.save(produit);
	}

	public Produit getById(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return produitRepo.findById(id).orElseThrow(() -> {
			throw new ProduitException();
		});
	}

	public Page<Produit> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new ProduitException();
		}
		return produitRepo.findAll(pageable);
	}

	public Page<Produit> getNextPage(Page<Produit> page) {
		if (page == null) {
			throw new ProduitException();
		}
		return produitRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Produit> getPrevious(Page<Produit> page) {
		if (page == null) {
			throw new ProduitException();
		}
		return produitRepo.findAll(page.previousOrFirstPageable());
	}

	public Produit update(Produit produit) {
		Produit produitEnBase = getById(produit.getId());
		produitEnBase.setLibelle(produit.getLibelle() != null ? produit.getLibelle() : produitEnBase.getLibelle());
		produitEnBase.setDescription(
				produit.getDescription() != null ? produit.getDescription() : produitEnBase.getDescription());
		produitEnBase.setPrix(produit.getPrix() != 0 ? produit.getPrix() : produitEnBase.getPrix());
		produitEnBase.setFournisseur(
				produit.getFournisseur() != null ? produit.getFournisseur() : produitEnBase.getFournisseur());
		produitEnBase.setAchats(produit.getAchats() != null ? produit.getAchats() : produitEnBase.getAchats());
		return produitRepo.save(produitEnBase);
	}

	public void delete(Produit produit) {
		checkProduitIsNotNull(produit);
		deleteById(produit.getId());
	}

	public void delete(Long id) {
		deleteById(id);
	}

	private void deleteById(Long id) {
		Produit produit = getById(id);
		produitRepo.delete(produit);
	}

	public List<Produit> findByLibelle(String libelle) {
		if (libelle == null) {
			throw new ProduitException();
		}
		return produitRepo.findByLibelle(libelle);
	}
}
