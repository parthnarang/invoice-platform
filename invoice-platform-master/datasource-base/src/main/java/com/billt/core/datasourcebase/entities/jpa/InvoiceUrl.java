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

public class InvoiceUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String transactionId;

    @Column(unique = true)
    String url;

    Date createdOn;
    Date updatedOn;

}
