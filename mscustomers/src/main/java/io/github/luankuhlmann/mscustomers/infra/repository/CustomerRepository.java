package io.github.luankuhlmann.mscustomers.infra.repository;

import io.github.luankuhlmann.mscustomers.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);
}
