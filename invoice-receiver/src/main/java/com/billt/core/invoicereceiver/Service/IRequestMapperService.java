package com.billt.core.invoicereceiver.Service;

import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.invoicereceiver.Exceptions.RequestDataMappingException;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Model.TransactionFlowRequestBean;

public interface IRequestMapperService {

    TransactionFlowRequestBean mapToTransactionFlowBean(InvoiceRequestBean invoiceRequestBean) throws RequestDataMappingException;
    Invoice mapToInvoiceCollection(TransactionFlowRequestBean flowRequestBean);


}
