package com.couse.microservices.msclients.application;

import com.couse.microservices.msclients.application.representation.ClientSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientResource {

    @Autowired
    private final ClientService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientSaveRequest request) {
        var client = request.toModel();
        service.saveClient(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @RequestMapping(params = "cpf")
    public ResponseEntity clientData(@RequestParam("cpf") String cpf){
        var client = service.getByCPF(cpf);
        if(client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);
    }
}
