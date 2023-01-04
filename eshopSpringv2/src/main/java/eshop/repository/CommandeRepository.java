package eshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Client;
import eshop.entity.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	@Modifying
	@Transactional
	@Query("delete Commande c where c.client=:client")
	void deleteByClient(@Param("client") Client client);

	@Modifying
	@Transactional
	Commande findByClient(Client client);
}
