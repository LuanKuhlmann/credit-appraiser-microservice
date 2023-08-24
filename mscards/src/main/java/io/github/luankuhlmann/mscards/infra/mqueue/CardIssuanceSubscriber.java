package io.github.luankuhlmann.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.luankuhlmann.mscards.domain.Card;
import io.github.luankuhlmann.mscards.domain.CardOrderData;
import io.github.luankuhlmann.mscards.domain.CustomerCard;
import io.github.luankuhlmann.mscards.infra.repository.CardRepository;
import io.github.luankuhlmann.mscards.infra.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardIssuanceSubscriber {

    private final CardRepository cardRepository;
    private final CustomerCardRepository customerCardRepository;

    @RabbitListener(queues = "${mq.queues.cards-issuance}")
    public void receiveIssuanceRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();

            CardOrderData data = mapper.readValue(payload, CardOrderData.class);
            Card card = cardRepository.findById(data.getCardId()).orElseThrow();
            CustomerCard customerCard = new CustomerCard();
            customerCard.setCard(card);
            customerCard.setCpf(data.getCpf());
            customerCard.setApprovedLimit(data.getApprovedLimit());

            customerCardRepository.save(customerCard);

        } catch (Exception e) {
           log.error("Error when receiving card issue request: {} ", e.getMessage());
        }
    }
}
