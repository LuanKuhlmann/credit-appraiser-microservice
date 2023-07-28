package io.github.luankuhlmann.mscards.application;

import io.github.luankuhlmann.mscards.domain.ClientCard;
import io.github.luankuhlmann.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private ClientCardRepository repository;

    public List<ClientCard> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
