package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.Service.MerchantService;
import com.billt.core.datasourcebase.Service.IUrlMapperService;
import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import com.billt.core.datasourcebase.repositories.mongo.write.InvoiceWriteRepository;
import com.billt.core.invoicereceiver.Exceptions.RequestDataMappingException;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Service.*;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;
import com.billt.core.notificationservice.Helpers.EmailHelper;
import com.billt.core.notificationservice.Helpers.SmsHelper;
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

    @Autowired
    CustomerReadRepository customerReadRepository;

    @Autowired
    EmailHelper emailHelper;

    @Autowired
    SmsHelper smsHelper;

    @Autowired
    @Qualifier(value="urlService")
    IUrlMapperService urlMapperService;


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


           notificationPush.pushNewInvoice(transactionFlowRequestBean);

           String uemail = "";
           if (transactionFlowRequestBean.getEmail() != null) {
               uemail = transactionFlowRequestBean.getEmail();
           } else if (transactionFlowRequestBean.getPhoneNo() != null) {
               String uphone = "";
               uphone = transactionFlowRequestBean.getPhoneNo();
               Customer customer = customerReadRepository.findCustomerByMobile(uphone);
               uemail = customer.getEmail();
           } else if (transactionFlowRequestBean.getCid() != null) {
               String ucid = "";
               ucid = transactionFlowRequestBean.getCid();
               Customer customer = customerReadRepository.findCustomerByMobile(ucid);
               uemail = customer.getEmail();
           }
           if (uemail.compareTo("") != 0) {
               String emailMessage = emailHelper.constructEmail(transactionFlowRequestBean);
               //emailSender.sendSimpleMessage(uemail,"Test mail","Hello this is a test mail for BillT");
               if(emailMessage.compareTo("")!=0)
                   emailSender.sendSimpleMessage(uemail,"Test mail",emailMessage);
           }

           saveNewInvoice(transactionFlowRequestBean);

           String transactionUrl = urlMapperService.mapUrl(transactionFlowRequestBean.getTransID());
           String sms = smsHelper.sendSms(transactionFlowRequestBean,transactionUrl);
           LOG.info("SMS helper return value: {}",sms);
       }
       catch (RequestDataMappingException e){
        return e.getResponseCode();
       }
       catch (Exception e){
           System.out.println("Dd");
           LOG.error("InvoiceServiceImpl exception processinvoicerequest ",e.getMessage());
       }
       return ResponseCode.TRANSACTION_SUCCESS;
    }

    public void saveNewInvoice(TransactionFlowRequestBean flowRequestBean){
        LOG.info("transaction flow bean = "+flowRequestBean.toString());
        Invoice newInvoice = requestMapperService.mapToInvoiceCollection(flowRequestBean);
        LOG.info("transaction flow bean = "+newInvoice.toString());
        invoiceWriteRepository.save(newInvoice);

   }
}
