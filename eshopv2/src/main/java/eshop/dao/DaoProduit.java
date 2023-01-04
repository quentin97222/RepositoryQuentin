package eshop.dao;

import java.util.List;

import eshop.entity.Produit;

public interface DaoProduit extends DaoGeneric<Produit, Long> {
	public List<Produit> findByLibelle(String libelle);
}
