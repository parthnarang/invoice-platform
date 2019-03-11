package com.billt.core.datasourcebase.entities.jpa;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    String mid;
    String merchantName;
    String merchantAddress;
    String password;

    @Transient
    private String passwordConfirm;

    String merchantLogo;
    String email;
    String contactList;
    String gstNo;
    String tinNo;
    Date createdOn;
    Date updatedOn;

}
