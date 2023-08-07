package io.github.luankuhlmann.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.cards-issuance}")
    private String cardIssuanceRow;

    @Bean
    public Queue queueCardIssuance() {
        return new Queue(cardIssuanceRow, true);
    }
}
