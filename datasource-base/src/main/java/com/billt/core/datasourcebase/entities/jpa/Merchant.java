package com.billt.core.datasourcebase.entities.jpa;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Created by parth
 */

@Getter
@Setter
@Data
@Entity

@Component
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    private String mid;

    @NotEmpty(message = "Please provide store name")
    private String merchantName;

    @NotEmpty(message = "Please provide store address")
    private String merchantAddress;

    private String password;

    @Transient
    private String passwordConfirm;

    private String merchantLogo;

    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    private String confirmationToken;

    private String contactList;
    private String gstNo;
    private String tinNo;
    private Date createdOn;
    private Date updatedOn;


    private String cgst;
    private String sgst;
    private String serviceCharge;
    private String vat;
}
