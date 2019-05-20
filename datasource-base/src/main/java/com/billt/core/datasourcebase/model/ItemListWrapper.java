package com.billt.core.datasourcebase.model;

import com.billt.core.datasourcebase.model.invoiceReceiver.InvoiceItem;
import lombok.Data;

import java.util.List;

@Data
public class ItemListWrapper {

    private List<InvoiceItem> invoiceItems;
}
