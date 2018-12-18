package com.billt.core.datasourcebase.entities.jpa;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by parth
 */

@Getter
@Setter
@Data
@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long merchantId;

    String merchantName;
    String mid;

    @ManyToOne
    @JoinColumn
    Vendor vendor;

    Date createdOn;
    String merchantAddress;
}
