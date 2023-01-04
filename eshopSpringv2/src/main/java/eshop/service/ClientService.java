package eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eshop.entity.Adresse;
import eshop.entity.Client;
import eshop.exception.ClientException;
import eshop.exception.IdException;
import eshop.repository.AchatRepository;
import eshop.repository.ClientRepository;
import eshop.repository.CommandeRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private CommandeRepository commandeRepo;

	@Autowired
	private AchatRepository achatRepo;

	private void checkClientIsNotNull(Client client) {
		if (client == null) {
			throw new ClientException();
		}
	}

	public void create(Client client) {
		checkClientIsNotNull(client);
		if (client.getPrenom() == null || client.getPrenom().isEmpty()) {
			throw new ClientException();
		}
		if (client.getNom() == null || client.getNom().isEmpty()) {
			throw new ClientException();
		}
		clientRepo.save(client);
	}

	public Client getById(Long id) {
		if (id == null) {
			throw new IdException();
		}
		return clientRepo.findById(id).orElseThrow(() -> {
			throw new ClientException();
		});
	}

	public Client update(Client client) {
		Client clientEnBase = getById(client.getId());
		clientEnBase.setNom(client.getPrenom() != null ? client.getPrenom() : clientEnBase.getPrenom());
		clientEnBase.setEmail(client.getEmail());
		if (client.getAdresse() != null) {
			clientEnBase.setAdresse(new Adresse(client.getAdresse().getNumero(), client.getAdresse().getRue(),
					client.getAdresse().getCodePostal(), client.getAdresse().getVille()));
		} else {
			clientEnBase.setAdresse(null);
		}
		clientEnBase.setPrenom(client.getPrenom() != null ? client.getPrenom() : clientEnBase.getPrenom());
		clientEnBase.setDateInscription(client.getDateInscription());
		clientEnBase.setCivilite(client.getCivilite());

		return clientRepo.save(clientEnBase);
	}

	public Client updatePassword(Client client) {
		Client clientEnBase = getById(client.getId());
		clientEnBase.setPassword(client.getPassword());
		return clientRepo.save(clientEnBase);
	}

	public void delete(Client client) {
		checkClientIsNotNull(client);
		deleteById(client.getId());
	}

	public void delete(Long id) {
		deleteById(id);
	}

	private void deleteById(Long id) {
		Client client = getById(id);
		commandeRepo.deleteByClient(client);
		achatRepo.deleteByCommande(commandeRepo.findByClient(client));
		clientRepo.delete(client);
	}

	public Page<Client> getAll(Pageable pageable) {
		if (pageable == null) {
			throw new ClientException();
		}
		return clientRepo.findAll(pageable);
	}

	public Page<Client> getNextPage(Page<Client> page) {
		if (page == null) {
			throw new ClientException();
		}
		return clientRepo.findAll(page.nextOrLastPageable());
	}

	public Page<Client> getPrevious(Page<Client> page) {
		if (page == null) {
			throw new ClientException();
		}
		return clientRepo.findAll(page.previousOrFirstPageable());
	}
}
