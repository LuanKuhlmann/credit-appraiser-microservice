package io.github.luankuhlmann.mscustomers.application;

import io.github.luankuhlmann.mscustomers.application.representation.CustomerSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerResource {

    @Autowired
    private final CustomerService service;

    @GetMapping
    public String status() {
        log.info("Getting status from customers microservice");
        return "ok";
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody CustomerSaveRequest request) {
        var customer = request.toModel();
        service.saveCustomer(customer);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(customer.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @RequestMapping(params = "cpf")
    public ResponseEntity customerData(@RequestParam("cpf") String cpf){
        var customer = service.getByCPF(cpf);
        if(customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }
}
