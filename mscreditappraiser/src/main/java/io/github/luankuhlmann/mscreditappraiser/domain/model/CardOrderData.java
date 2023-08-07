package io.github.luankuhlmann.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardOrderData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal approvedLimit;
}
