package io.github.luankuhlmann.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerAppraisalResult {
    private List<ApprovedCard> cards;
}
