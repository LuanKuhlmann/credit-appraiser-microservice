package io.github.luankuhlmann.mscreditappraiser.infra.clients.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.luankuhlmann.mscreditappraiser.domain.model.CardIssueData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuancePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardIssuance;

    public void issueCard(CardIssueData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueCardIssuance.getName(), json);
    }

    private String convertIntoJson(CardIssueData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
