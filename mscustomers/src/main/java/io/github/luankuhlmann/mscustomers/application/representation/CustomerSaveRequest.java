package io.github.luankuhlmann.mscustomers.application.representation;

import io.github.luankuhlmann.mscustomers.domain.Customer;
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
