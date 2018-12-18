package com.billt.core.invoicereceiver.Service;

import com.billt.core.billtcore.Model.invoiceReceiver.InvoiceRequestBean;
import com.billt.core.billtcore.enums.invoiceReceiver.ValidationResults;
import com.billt.core.invoicereceiver.utils.Response;

public interface IInvoiceService {

    Response processInvoiceRequest(InvoiceRequestBean requestData);

    ValidationResults validatePaymentRequest(InvoiceRequestBean requestData);
}
