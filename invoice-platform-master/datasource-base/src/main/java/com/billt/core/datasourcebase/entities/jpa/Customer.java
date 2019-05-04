package com.billt.core.datasourcebase.entities.jpa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Data
@Entity

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String cid;

    String firstName;
    String lastName;
    String email;
    String mobile;

    Date createdOn;
    Date updatedOn;

}
