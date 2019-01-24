package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.repositories.mongo.write.InvoiceWriteRepository;
import com.billt.core.invoicereceiver.Exceptions.RequestDataMappingException;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Service.*;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;
import com.billt.core.notificationservice.Services.EmailSender;
import com.billt.core.notificationservice.Services.NotificationPush;
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

    @Autowired
    InvoiceWriteRepository invoiceWriteRepository;

    @Autowired
    @Qualifier(value="customerService")
    ICustomerService iCustomerService;

    @Autowired
    NotificationPush notificationPush;

    @Autowired
    EmailSender emailSender;

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    public ValidationResults validatePaymentRequest(InvoiceRequestBean requestData){

        if(!validationService.checksumValidatation(requestData))
            return ValidationResults.CHECKSUM_VALIDATION_FAILURE;

        if(!validationService.validateInvoiceData(requestData))
           return ValidationResults.INVALID_DATA;

        return ValidationResults.VALIDATION_SUCCESS;
    }

    public ResponseCode processInvoiceRequest(InvoiceRequestBean requestData){

        LOG.info("Invoice Request Received for order id : {}", requestData.getOrderId());

     TransactionFlowRequestBean transactionFlowRequestBean = null;
       try {
           transactionFlowRequestBean = requestMapperService.mapToTransactionFlowBean(requestData);
           //saveNewInvoice(transactionFlowRequestBean);

           notificationPush.pushNewInvoice(transactionFlowRequestBean);
           emailSender.sendSimpleMessage("norirahul@gmail.com","Test msil","Hello this is a test mail for BillT");
       }
       catch (RequestDataMappingException e){
        return e.getResponseCode();
       }
       catch (Exception e){
   System.out.println("Dd");
       }
       return ResponseCode.TRANSACTION_SUCCESS;
    }

    public void saveNewInvoice(TransactionFlowRequestBean flowRequestBean){

        Invoice newInvoice = requestMapperService.mapToInvoiceCollection(flowRequestBean);
        invoiceWriteRepository.save(newInvoice);

   }
}
