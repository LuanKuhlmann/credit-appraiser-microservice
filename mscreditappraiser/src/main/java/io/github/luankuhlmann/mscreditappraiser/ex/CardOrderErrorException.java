package io.github.luankuhlmann.mscreditappraiser.ex;

public class CardOrderErrorException extends RuntimeException{
    public CardOrderErrorException(String message) {
        super(message);
    }
}
