package com.billt.core.invoicereceiver.Service;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Model.TransactionFlowRequestBean;

public interface IRequestMapperService {

    TransactionFlowRequestBean MapToTransactionFlowBean(InvoiceRequestBean invoiceRequestBean);


}
