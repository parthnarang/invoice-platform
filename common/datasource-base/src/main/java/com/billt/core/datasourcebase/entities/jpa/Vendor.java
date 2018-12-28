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
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long vendorId;

    String vendorName;

    @Column(unique = true)
    String vid;

    Date createdOn;

    String contact1;

    String contact2;

    String email;
}
