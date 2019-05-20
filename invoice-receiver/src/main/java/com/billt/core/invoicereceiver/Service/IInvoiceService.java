package com.billt.core.invoicereceiver.Service;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;

import java.io.IOException;

public interface IInvoiceService {

    ResponseCode processInvoiceRequest(InvoiceRequestBean requestData) throws IOException;

    ValidationResults validatePaymentRequest(InvoiceRequestBean requestData);


}
