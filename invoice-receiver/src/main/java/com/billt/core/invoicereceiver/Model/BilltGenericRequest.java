package com.billt.core.invoicereceiver.Model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Generic class to map the incoming request.
 *
 * @author Rahul Nori
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BilltGenericRequest<R> implements Serializable {
    /**
     * Serial version uid
     */
    private static final long serialVersionUID = 3656029836691492535L;

    @NotNull(message = "{notnull}")
    @Valid
    private R request;

    /**
     * @return the request
     */
    public R getRequest() {
        return request;
    }

    /**
     * @param request
     * the request to set
     */
    public void setRequest(R request) {
        this.request = request;
    }
}
