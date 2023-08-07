package io.github.luankuhlmann.mscards.application;

import io.github.luankuhlmann.mscards.domain.CustomerCard;
import io.github.luankuhlmann.mscards.infra.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardService {

    private final CustomerCardRepository repository;

    public List<CustomerCard> listCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
