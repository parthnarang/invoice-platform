package com.billt.core.merchantpanel.Entities;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import lombok.Data;
import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn
    Merchant merchant;

    String categoryname;

    Date createdOn;
    Date updatedOn;

}
