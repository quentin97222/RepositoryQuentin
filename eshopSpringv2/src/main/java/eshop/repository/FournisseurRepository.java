package eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

}
