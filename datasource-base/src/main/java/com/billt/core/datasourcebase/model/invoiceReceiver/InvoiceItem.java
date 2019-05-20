package com.billt.core.datasourcebase.model.invoiceReceiver;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

public class InvoiceItem {

     private String DESCRIPTION;
     private String QUANTITY;
     private String RATE;
     private String AMOUNT;
     private String DISCOUNT;
public InvoiceItem(){

}

}
