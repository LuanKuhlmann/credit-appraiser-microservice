package io.github.luankuhlmann.msclients.application;

import io.github.luankuhlmann.msclients.domain.Customer;
import io.github.luankuhlmann.msclients.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public Customer saveCustomer(Customer customer) {

        return repository.save(customer);
    }

    public Optional<Customer> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
