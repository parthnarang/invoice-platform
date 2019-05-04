package com.billt.core.invoicereceiver.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Contains computable data received in the Request
 *
 * @author Rahul Nori
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequestPayload<H, B> implements Serializable {
    /**
     * Serial version uid
     */
    private static final long serialVersionUID = -3026225570859769831L;

    @NotNull(message = "{notnull}")
    @Valid
    H head;

    @NotNull(message = "{notnull}")
    @Valid
    B body;

    /**
     * @return the head
     */
    public H getHead() {
        return head;
    }

    /**
     * @param head
     * the head to set
     */
    public void setHead(H head) {
        this.head = head;
    }

    /**
     * @return the body
     */
    public B getBody() {
        return body;
    }

    /**
     * @param body
     * the body to set
     */
    public void setBody(B body) {
        this.body = body;
    }
}

