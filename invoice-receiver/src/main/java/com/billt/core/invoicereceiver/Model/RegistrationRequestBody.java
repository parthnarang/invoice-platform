package com.billt.core.invoicereceiver.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amit.dubey
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class RegistrationRequestBody {
    /**
     * serial version UID
     */
    private static final long serialVersionUID = -7521004472800537742L;

    /*@NotBlank(message = "{notblank}")
    private String createdTime;*/

    @NotBlank(message = "{notblank}")
    private String firstName;

    @NotBlank(message = "{notblank}")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /*
    /**
     * @return the createdTime
     */
    /*
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     * the createdTime to set
     */
    /*
    public void setCreatedTime(String createdTime) {
        this.createdTime
                */

    }


