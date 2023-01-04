package eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
