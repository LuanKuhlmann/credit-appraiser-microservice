package io.github.luankuhlmann.msclients.infra.repository;

import io.github.luankuhlmann.msclients.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCpf(String cpf);
}
