package io.github.luankuhlmann.mscreditappraiser.application;

import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    public CustomerSituation getCustomerSituation(String cpf) {
        // get client data -mscustomers
        // get client cards -mscards
    }
}
