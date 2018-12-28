package com.billt.core.invoicereceiver.Service;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;

public interface IValidationService {

    boolean checksumValidatation(final InvoiceRequestBean requestData);

    boolean validateInvoiceData(final InvoiceRequestBean requestData);
}
