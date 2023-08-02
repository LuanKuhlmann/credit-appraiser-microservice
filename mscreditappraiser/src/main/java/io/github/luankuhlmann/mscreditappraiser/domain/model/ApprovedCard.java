package io.github.luankuhlmann.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {
    private String card;
    private String flag;
    private BigDecimal approvedLimit;
}
