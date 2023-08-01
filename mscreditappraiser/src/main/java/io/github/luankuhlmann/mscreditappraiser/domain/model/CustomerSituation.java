package io.github.luankuhlmann.mscreditappraiser.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerSituation {

    private CustomerData customer;
    private List<CustomerCard> cards;
}
