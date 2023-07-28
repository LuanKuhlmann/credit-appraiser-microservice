package io.github.luankuhlmann.mscards.application.representation;

import io.github.luankuhlmann.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerClientResponseDto {

    private String name;
    private String flag;
    private BigDecimal approvedLimit;

    public static CardsPerClientResponseDto fromModel(ClientCard model) {
        return new CardsPerClientResponseDto(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getApprovedLimit()
        );
    }
}
