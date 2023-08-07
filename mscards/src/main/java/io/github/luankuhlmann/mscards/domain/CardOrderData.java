package io.github.luankuhlmann.mscards.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardOrderData {
    private Long cardId;
    private String spf;
    private String address;
    private BigDecimal approvedLimit;
}
