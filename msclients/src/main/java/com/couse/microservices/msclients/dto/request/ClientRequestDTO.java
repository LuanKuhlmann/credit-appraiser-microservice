package com.couse.microservices.msclients.dto.request;

public record ClientRequestDTO (
        String cpf,
        String name,
        Integer age) {
}
