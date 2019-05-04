package com.billt.core.merchantpanel.Entities;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class MenuItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @ManyToOne
    @JoinColumn
    CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn
    Merchant merchant;

    Integer price;

    String category;

    Date createdOn;
    Date updatedOn;
}
