package com.couse.microservices.msclients.infra.repository;

import com.couse.microservices.msclients.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCpf(String cpf);
}
