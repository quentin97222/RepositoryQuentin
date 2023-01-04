package eshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.JpaConfig;
import eshop.repository.ClientRepository;

@SpringJUnitConfig(JpaConfig.class)
class ProduitServiceTest {

//	@Autowired
//	private ProduitService produitService;
	@Autowired
	private ClientRepository produitRepo;

	@Test
	void test() {
		assertNotNull(produitRepo);
	}

}
