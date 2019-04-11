package com.billt.core.invoicereceiver.Model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author rahul.nori
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequestHeader implements Serializable {
    /**
     * Serial version uid
     */
    private static final long serialVersionUID = 5560788360596434059L;

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}




