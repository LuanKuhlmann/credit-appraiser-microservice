package io.github.luankuhlmann.mscreditappraiser.ex;

public class CustomerDataNotFoundException extends Exception {
    public CustomerDataNotFoundException() {
        super("Customer data not found for given CPF");
    }
}
