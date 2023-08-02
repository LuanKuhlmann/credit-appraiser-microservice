package io.github.luankuhlmann.mscreditappraiser.application;

import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerSituation;
import io.github.luankuhlmann.mscreditappraiser.ex.CustomerDataNotFoundException;
import io.github.luankuhlmann.mscreditappraiser.ex.MicroservicesCommunicationErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        CustomerSituation customerSituation = null;
        try {
            customerSituation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesCommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
