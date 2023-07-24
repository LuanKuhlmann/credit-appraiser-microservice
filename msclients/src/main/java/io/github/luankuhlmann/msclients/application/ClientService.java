package io.github.luankuhlmann.msclients.application;

import io.github.luankuhlmann.msclients.domain.Client;
import io.github.luankuhlmann.msclients.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public Client saveClient(Client client) {

        return repository.save(client);
    }

    public Optional<Client> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
