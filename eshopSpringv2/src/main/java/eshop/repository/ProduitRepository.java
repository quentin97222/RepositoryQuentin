package eshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	List<Produit> findByLibelle(String libelle);
}
