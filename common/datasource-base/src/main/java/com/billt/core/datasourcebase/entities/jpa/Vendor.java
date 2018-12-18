package com.billt.core.datasourcebase.entities.jpa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    String vid;

    Date createdOn;

    String contact1;

    String contact2;

    String email;
}
