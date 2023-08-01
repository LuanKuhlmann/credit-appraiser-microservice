package io.github.luankuhlmann.mscards.application.representation;

import io.github.luankuhlmann.mscards.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPerCustomerResponseDto {

    private String name;
    private String flag;
    private BigDecimal approvedLimit;

    public static CardsPerCustomerResponseDto fromModel(CustomerCard model) {
        return new CardsPerCustomerResponseDto(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getApprovedLimit()
        );
    }
}
