package io.github.luankuhlmann.mscreditappraiser.application;

import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerData;
import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerSituation;
import io.github.luankuhlmann.mscreditappraiser.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;
    public CustomerSituation getCustomerSituation(String cpf) {
        // get client data -mscustomers
        // get client cards -mscards

        ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);

        return CustomerSituation
                .builder()
                .customer(customerDataResponse.getBody())
                .build();
    }
}
