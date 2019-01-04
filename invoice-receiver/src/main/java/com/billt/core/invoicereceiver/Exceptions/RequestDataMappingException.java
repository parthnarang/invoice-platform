package com.billt.core.invoicereceiver.Exceptions;

import com.billt.core.invoicereceiver.enums.ResponseCode;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
public class RequestDataMappingException extends Exception {

    ResponseCode responseCode;

    public RequestDataMappingException() {
        super();
    }
    public RequestDataMappingException(ResponseCode responseCode){
        super();
        this.responseCode = responseCode;
    }
}
