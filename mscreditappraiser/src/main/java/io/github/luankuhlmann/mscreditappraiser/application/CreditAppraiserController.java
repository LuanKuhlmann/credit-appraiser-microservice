package io.github.luankuhlmann.mscreditappraiser.application;

import io.github.luankuhlmann.mscreditappraiser.domain.model.*;
import io.github.luankuhlmann.mscreditappraiser.ex.CardOrderErrorException;
import io.github.luankuhlmann.mscreditappraiser.ex.CustomerDataNotFoundException;
import io.github.luankuhlmann.mscreditappraiser.ex.MicroservicesCommunicationErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity CustomerSituationQuery(@RequestParam("cpf") String cpf) {
        try {
            CustomerSituation customerSituation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity appraiseCustomer(@RequestBody AppraiserData data) {
        try {
            CustomerAppraisalResult customerAppraisalResult = creditAppraiserService
                    .appraiseCustomer(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(customerAppraisalResult);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("issue-card")
    public ResponseEntity issueCard(@RequestBody CardIssueData data) {
        try {
            CardIssueProtocol cardIssueProtocol = creditAppraiserService
                    .cardOrderIssuance(data);
            return ResponseEntity.ok(cardIssueProtocol);
        } catch (CardOrderErrorException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
