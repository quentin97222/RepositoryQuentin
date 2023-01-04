package eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Achat;
import eshop.entity.AchatKey;

public interface AchatRepository extends JpaRepository<Achat, AchatKey> {

}
