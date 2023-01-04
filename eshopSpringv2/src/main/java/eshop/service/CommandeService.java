package eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import eshop.entity.Commande;
import eshop.entity.Fournisseur;
import eshop.exception.CommandeException;
import eshop.exception.FournisseurException;
import eshop.exception.IdException;
import eshop.repository.CommandeRepository;
import eshop.repository.FournisseurRepository;




public class CommandeService {

	
	@Autowired
	private CommandeRepository commandeRepo;
	
	
	public void create(Commande commande) {
		checkCommandeIsNotNull(commande);
		if (commande.getClient() == null) {
			throw new CommandeException("client vide");
		}
		if (commande.getAchats() == null) {
			throw new CommandeException("achats vide");
		}
		commandeRepo.save(commande);
	}
	
	private void checkCommandeIsNotNull(Commande commande) {
		if (commande == null) {
			throw new CommandeException("formateur null");
		}
	}
	
	public Commande getById(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return commandeRepo.findById(id).orElseThrow(() -> {
			throw new CommandeException("formateur inconnu");
		});
	}
	
	public void delete(Commande commande) {
		checkCommandeIsNotNull(commande);
		deleteById(commande.getNumero());
	}

	public void delete(Long id) {
		deleteById(id);
	}

	private void deleteById(Long id) {
		Commande commande = getById(id);
		commandeRepo.delete(commande);
	}
	
	public List<Commande> getAll() {
		return commandeRepo.findAll();
	}

	public Page<Commande> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new CommandeException();
		}
		return commandeRepo.findAll(pageable);
	}

	public Page<Commande> getNextPage(Page<Commande> page) {
		if (page == null) {
			throw new CommandeException();
		}
		return commandeRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Commande> getPrevious(Page<Commande> page) {
		if (page == null) {
			throw new CommandeException();
		}
		return commandeRepo.findAll(page.previousOrFirstPageable());
	}
	
	public Commande update(Commande commande) {

		Commande commandeEnBase = getById(commande.getNumero());
		commandeEnBase.setClient(commande.getClient() != null ? commande.getClient() : commandeEnBase.getClient());
		commandeEnBase.setAchats(commande.getAchats() != null ? commande.getAchats() : commandeEnBase.getAchats());
		commandeEnBase.setDate(commande.getDate());
		return commandeRepo.save(commandeEnBase);

	}

}
