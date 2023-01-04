package eshop.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.entity.Achat;
import eshop.entity.AchatKey;
import eshop.entity.Commande;

public interface AchatRepository extends JpaRepository<Achat, AchatKey> {
	@Modifying
	@Transactional
	@Query("delete Achat a where a.commande=:commande")
	void deleteByCommande(@Param("commande") Commande commande);
}
