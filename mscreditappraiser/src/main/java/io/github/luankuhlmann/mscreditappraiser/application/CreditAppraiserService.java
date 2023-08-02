package io.github.luankuhlmann.mscreditappraiser.application;

import feign.FeignException;
import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerCard;
import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerData;
import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerSituation;
import io.github.luankuhlmann.mscreditappraiser.ex.CustomerDataNotFoundException;
import io.github.luankuhlmann.mscreditappraiser.ex.MicroservicesCommunicationErrorException;
import io.github.luankuhlmann.mscreditappraiser.infra.clients.CardResourceClient;
import io.github.luankuhlmann.mscreditappraiser.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;
    private final CardResourceClient cardClient;
    public CustomerSituation getCustomerSituation(String cpf) throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException {
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
            ResponseEntity<List<CustomerCard>> cardResponse = cardClient.getCardsByCustomer(cpf);

            return CustomerSituation
                    .builder()
                    .customer(customerDataResponse.getBody())
                    .cards(cardResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }
            throw new MicroservicesCommunicationErrorException(e.getMessage(), status);
        }
    }
}
