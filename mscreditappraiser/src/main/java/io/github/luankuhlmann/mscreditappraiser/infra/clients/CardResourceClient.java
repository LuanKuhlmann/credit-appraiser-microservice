package io.github.luankuhlmann.mscreditappraiser.infra.clients;

import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);

}
