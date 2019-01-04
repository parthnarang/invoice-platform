package com.billt.core.invoicereceiver.Model;

import com.billt.core.invoicereceiver.enums.ResponseCode;
import lombok.Data;

@Data
public class ResponseBean {

    boolean isSuccessful;
    ResponseCode responseCode;

}
