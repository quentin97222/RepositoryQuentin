package eshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eshop.repository.ProduitRepository;

class ProduitServiceTest {

//	@Autowired
//	private ProduitService produitService;
	@Autowired
	private ProduitRepository produitRepo;

	@Test
	void test() {
		assertNotNull(produitRepo);
	}

}
