package com.billt.core.invoicereceiver.Model;

import lombok.Data;

@Data
public class ResponseMessage {

    public boolean isSuccessful;
    boolean status;
    String message;

    public ResponseMessage(boolean isSuccessful){
        this.isSuccessful = isSuccessful;
    }
}
