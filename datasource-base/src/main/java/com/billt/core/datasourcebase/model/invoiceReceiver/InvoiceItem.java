package com.billt.core.datasourcebase.model.invoiceReceiver;

import lombok.Data;

@Data
public class InvoiceItem {
    String DESCRIPTION;
    String QUANTITY;
    String RATE;
    String AMOUNT;
    public InvoiceItem(String DESCRIPTION,String QUANTITY,String RATE,String AMOUNT){

        this.DESCRIPTION = DESCRIPTION;
        this.QUANTITY = QUANTITY;
        this.RATE = RATE;
        this.AMOUNT = AMOUNT;
    }
}
