package io.github.luankuhlmann.mscreditappraiser.ex;

import lombok.Getter;

public class MicroservicesCommunicationErrorException extends Exception{

    @Getter
    private Integer status;

    public MicroservicesCommunicationErrorException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
