package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Model.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Service.IRequestMapperService;
import com.billt.core.invoicereceiver.Service.MerchantService;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;
import com.billt.core.invoicereceiver.Service.IInvoiceService;
import com.billt.core.invoicereceiver.Service.IValidationService;
import com.billt.core.invoicereceiver.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("invoiceService")
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    @Qualifier(value = "validationservice")
    IValidationService validationService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    @Qualifier(value = "requestmappperservice")
    IRequestMapperService requestMapperService;



    private static final Logger LOG = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    public ValidationResults validatePaymentRequest(InvoiceRequestBean requestData){

        if(!validationService.checksumValidatation(requestData))
            return ValidationResults.CHECKSUM_VALIDATION_FAILURE;

        if(!validationService.validateInvoiceData(requestData))
           return ValidationResults.INVALID_DATA;

        return ValidationResults.VALIDATION_SUCCESS;
    }

    public Response processInvoiceRequest(InvoiceRequestBean requestData){
        LOG.debug("Invoice Request Received for order id : {}", requestData.getOrderId());

        Boolean validate = merchantService.findMerchantByMid(requestData.getMid());

        if(validate){
            
            TransactionFlowRequestBean transactionFlowRequestBean = requestMapperService.MapToTransactionFlowBean(requestData);
        }



   return null;
    }
}
