package io.github.luankuhlmann.msclients.infra.repository;

import io.github.luankuhlmann.msclients.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);
}
