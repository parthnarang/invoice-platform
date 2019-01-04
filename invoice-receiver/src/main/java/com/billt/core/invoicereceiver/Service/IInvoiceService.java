package com.billt.core.invoicereceiver.Service;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;

public interface IInvoiceService {

    ResponseCode processInvoiceRequest(InvoiceRequestBean requestData);

    ValidationResults validatePaymentRequest(InvoiceRequestBean requestData);


}
