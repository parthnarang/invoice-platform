package com.billt.core.invoicereceiver.Service;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;
import com.billt.core.invoicereceiver.utils.Response;

public interface IInvoiceService {

    Response processInvoiceRequest(InvoiceRequestBean requestData);

    ValidationResults validatePaymentRequest(InvoiceRequestBean requestData);
}
