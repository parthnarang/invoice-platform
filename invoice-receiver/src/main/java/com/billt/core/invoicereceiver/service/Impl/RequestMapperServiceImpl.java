package com.billt.core.invoicereceiver.service.Impl;

import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Exceptions.RequestDataMappingException;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.service.ICustomerService;
import com.billt.core.invoicereceiver.service.IRequestMapperService;
import com.billt.core.datasourcebase.services.MerchantService;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service("requestmappperservice")
public class RequestMapperServiceImpl implements IRequestMapperService {

    private static final Logger LOG = LoggerFactory.getLogger(RequestMapperServiceImpl.class);

    @Autowired
    MerchantService merchantService;

    @Autowired
    @Qualifier(value ="customerService")
    ICustomerService customerService;


    public TransactionFlowRequestBean mapToTransactionFlowBean(InvoiceRequestBean invoiceRequestBean) throws RequestDataMappingException {

        Boolean validMid = merchantService.findMerchantByMid(invoiceRequestBean.getMid());

        if(!validMid){
            LOG.error("Invalid Mid", invoiceRequestBean.getMid());
            throw new RequestDataMappingException(ResponseCode.INVALID_MID);
        }

        Customer customer =customerService.checkIfCustomerExist(invoiceRequestBean.getMobileNo(),invoiceRequestBean.getEmail());

        TransactionFlowRequestBean transactionFlowRequestBean = new TransactionFlowRequestBean();

        transactionFlowRequestBean.setMid(invoiceRequestBean.getMid());
        transactionFlowRequestBean.setVid(invoiceRequestBean.getVid());
        transactionFlowRequestBean.setOrderId(invoiceRequestBean.getOrderId());
        transactionFlowRequestBean.setMerchantName(invoiceRequestBean.getMerchantName());
        transactionFlowRequestBean.setAddress(invoiceRequestBean.getAddress());
        transactionFlowRequestBean.setPhoneNo(invoiceRequestBean.getPhoneNoList());
        transactionFlowRequestBean.setGst(invoiceRequestBean.getGst());
        transactionFlowRequestBean.setTime(invoiceRequestBean.getTime());
        transactionFlowRequestBean.setDate(invoiceRequestBean.getDate());
        transactionFlowRequestBean.setTotalAmt(invoiceRequestBean.getTotalAmt().toString());
        transactionFlowRequestBean.setVat(invoiceRequestBean.getVat().toString());
        transactionFlowRequestBean.setNet(invoiceRequestBean.getNet().toString());
        transactionFlowRequestBean.setInvoiceItems(invoiceRequestBean.getItemListWrapper().getInvoiceItems());

        if(customer!=null){
            transactionFlowRequestBean.setCid(customer.getCid());
        }

        if(invoiceRequestBean.getCustomerEmail() != null){
            transactionFlowRequestBean.setEmail(invoiceRequestBean.getCustomerEmail());
        }
        if(invoiceRequestBean.getCustomerMobileNO()!= null){
            transactionFlowRequestBean.setMobileNo(invoiceRequestBean.getCustomerMobileNO());
        }

        transactionFlowRequestBean.setTransID(UUID.randomUUID().toString());
        transactionFlowRequestBean.setBilltDate(new Date());

        return transactionFlowRequestBean;
    }

    public Invoice mapToInvoiceCollection(TransactionFlowRequestBean flowRequestBean){

        Invoice invoice = new Invoice();

        invoice.setMid(flowRequestBean.getMid());
        invoice.setVid(flowRequestBean.getVid());
        invoice.setOrderId(flowRequestBean.getOrderId());
        invoice.setTransID(flowRequestBean.getTransID());
        invoice.setDate(flowRequestBean.getDate());
        invoice.setMerchantName(flowRequestBean.getMerchantName());
        invoice.setAddress(flowRequestBean.getAddress());
        invoice.setPhoneNo(flowRequestBean.getPhoneNo());
        invoice.setGst(flowRequestBean.getGst());
        invoice.setTime(flowRequestBean.getTime());
        invoice.setDate(flowRequestBean.getDate());
        invoice.setTotalAmt(flowRequestBean.getTotalAmt());
        invoice.setVat(flowRequestBean.getVat());
        invoice.setNet(flowRequestBean.getNet());
        invoice.setInvoiceItems(flowRequestBean.getInvoiceItems());
        invoice.setBilltDate(flowRequestBean.getBilltDate());

        if(flowRequestBean.getMobileNo()!=null)
            invoice.setMobile(flowRequestBean.getMobileNo());

        if(flowRequestBean.getEmail()!= null)
            invoice.setEmail(flowRequestBean.getEmail());

        if(flowRequestBean.getCid()!= null)
            invoice.setCid(flowRequestBean.getCid());


        return invoice;

    }
}
