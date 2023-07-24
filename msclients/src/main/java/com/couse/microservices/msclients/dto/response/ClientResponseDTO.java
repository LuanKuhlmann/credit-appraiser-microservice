package com.couse.microservices.msclients.dto.response;

import com.couse.microservices.msclients.domain.Client;

public record ClientResponseDTO(
        Long id,
        String cpf,
        String name,
        Integer age
) {

    public ClientResponseDTO(Client response) {
        this(
                response.getId(),
                response.getCpf(),
                response.getName(),
                response.getAge()
        );
    }
}
