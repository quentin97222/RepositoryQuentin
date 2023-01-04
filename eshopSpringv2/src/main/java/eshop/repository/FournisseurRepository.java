package eshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eshop.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	List<Fournisseur> findByContact(String contact);

	@Query("select f from Fournisseur f left join fetch f.produits where f.id=:id")
	Optional<Fournisseur> findByIdFetchProduits(Long id);
	
}
