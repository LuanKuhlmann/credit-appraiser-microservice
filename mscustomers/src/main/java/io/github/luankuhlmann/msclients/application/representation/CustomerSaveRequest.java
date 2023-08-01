package io.github.luankuhlmann.msclients.application.representation;

import io.github.luankuhlmann.msclients.domain.Customer;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Customer toModel() {
        return new Customer(cpf, name, age);
    }
}
