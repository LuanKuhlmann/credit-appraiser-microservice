package io.github.luankuhlmann.mscreditappraiser.application;

import feign.FeignException;
import io.github.luankuhlmann.mscreditappraiser.domain.model.*;
import io.github.luankuhlmann.mscreditappraiser.ex.CardOrderErrorException;
import io.github.luankuhlmann.mscreditappraiser.ex.CustomerDataNotFoundException;
import io.github.luankuhlmann.mscreditappraiser.ex.MicroservicesCommunicationErrorException;
import io.github.luankuhlmann.mscreditappraiser.infra.clients.CardResourceClient;
import io.github.luankuhlmann.mscreditappraiser.infra.clients.CustomerResourceClient;
import io.github.luankuhlmann.mscreditappraiser.infra.clients.mqueue.CardIssuancePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;
    private final CardResourceClient cardClient;
    private final CardIssuancePublisher cardIssuancePublisher;

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

    public CustomerAppraisalResult appraiseCustomer(String cpf, Long income) throws CustomerDataNotFoundException, MicroservicesCommunicationErrorException{
       try{
           ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
           ResponseEntity<List<Card>> cardResponse = cardClient.getCardMaxIncome(income);

           List<Card> cards = cardResponse.getBody();
           var ApprovedCardsList = cards.stream().map(card -> {

               CustomerData customerData = customerDataResponse.getBody();

               BigDecimal basicLimit = card.getBasicLimit();
               BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());
               var factor =  ageBD.divide(BigDecimal.valueOf(10));
               BigDecimal approvedLimitBD = factor.multiply(basicLimit);

               ApprovedCard approved = new ApprovedCard();
               approved.setCard(card.getName());
               approved.setFlag(card.getFlag());
               approved.setApprovedLimit(approvedLimitBD);

               return approved;
           }).collect(Collectors.toList());

           return new CustomerAppraisalResult(ApprovedCardsList);

       } catch (FeignException.FeignClientException e) {
           int status = e.status();
           if(HttpStatus.NOT_FOUND.value() == status) {
               throw new CustomerDataNotFoundException();
           }
           throw new MicroservicesCommunicationErrorException(e.getMessage(), status);
       }
    }

    public CardOrderProtocol orderCardIssuance(CardOrderData data) {
        try {
            cardIssuancePublisher.orderCard(data);
            var protocol = UUID.randomUUID().toString();
            return new CardOrderProtocol(protocol);
        } catch (Exception e) {
            throw new CardOrderErrorException(e.getMessage());
        }
    }
}
