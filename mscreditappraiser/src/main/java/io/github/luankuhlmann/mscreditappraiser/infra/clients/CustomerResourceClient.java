package io.github.luankuhlmann.mscreditappraiser.infra.clients;

import io.github.luankuhlmann.mscreditappraiser.domain.model.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> customerData(@RequestParam("cpf") String cpf);

}
