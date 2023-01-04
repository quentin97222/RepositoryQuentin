package eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
