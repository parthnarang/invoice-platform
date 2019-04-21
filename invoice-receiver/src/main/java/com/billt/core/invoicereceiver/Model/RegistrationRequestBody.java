package com.billt.core.invoicereceiver.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;


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

    @NotBlank(message = "{notblank}")
    private String email;

    @NotBlank(message = "{notblank}")
    private String phoneNo;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }
}


