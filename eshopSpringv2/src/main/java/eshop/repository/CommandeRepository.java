package eshop.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Client;
import eshop.entity.Commande;
import eshop.entity.Fournisseur;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	@Modifying
	@Transactional
	@Query("delete Commande c where c.client=:client")
	void deleteByClient(@Param("client") Client client);

	@Modifying
	@Transactional
	Commande findByClient(Client client);
	
	@Query("select f from Fournisseur f left join fetch f.produits where f.id=:id")
	Optional<Commande> findByIdFetchAchats(Long id);
}
