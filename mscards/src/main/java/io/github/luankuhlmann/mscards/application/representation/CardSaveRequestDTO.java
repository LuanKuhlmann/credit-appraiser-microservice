package io.github.luankuhlmann.mscards.application.dto;

import io.github.luankuhlmann.mscards.domain.Card;
import io.github.luankuhlmann.mscards.domain.CardFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequestDTO {
    private String name;
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal limit;

    public Card toModel() {
        return new Card(name, flag, income, limit);
    }
}
