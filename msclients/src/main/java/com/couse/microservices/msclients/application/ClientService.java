package com.couse.microservices.msclients.application;

import com.couse.microservices.msclients.domain.Client;
import com.couse.microservices.msclients.dto.request.ClientRequestDTO;
import com.couse.microservices.msclients.dto.response.ClientResponseDTO;
import com.couse.microservices.msclients.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public ClientResponseDTO saveClient(Client client) {

        var response = repository.save(client);

        return new ClientResponseDTO(response);
    }

    public Optional<Client> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}