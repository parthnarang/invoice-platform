package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.invoicereceiver.Exceptions.RequestDataMappingException;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Service.ICustomerService;
import com.billt.core.invoicereceiver.Service.IRequestMapperService;
import com.billt.core.invoicereceiver.Service.MerchantService;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;


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

        if(customer!=null){
            transactionFlowRequestBean.setCustId(customer.getId());
        }

        if(invoiceRequestBean.getEmail() != null){
            transactionFlowRequestBean.setEmail(invoiceRequestBean.getEmail());
        }
        if(invoiceRequestBean.getMobileNo()!= null){
            transactionFlowRequestBean.setMobileNo(invoiceRequestBean.getMobileNo());
        }
        transactionFlowRequestBean.setData(invoiceRequestBean.getData());

        transactionFlowRequestBean.setTransID("123y34747464362");
        transactionFlowRequestBean.setDate(new Date());

        return transactionFlowRequestBean;
    }

    public Invoice mapToInvoiceCollection(TransactionFlowRequestBean flowRequestBean){

        Invoice invoice = new Invoice();

        invoice.setMid(flowRequestBean.getMid());
        invoice.setVid(flowRequestBean.getVid());
        invoice.setOrderId(flowRequestBean.getOrderId());
        invoice.setTransID(flowRequestBean.getTransID());
        invoice.setDate(flowRequestBean.getDate());

        if(flowRequestBean.getMobileNo()!=null)
            invoice.setMobile(flowRequestBean.getMobileNo());

        if(flowRequestBean.getEmail()!= null)
            invoice.setEmail(flowRequestBean.getEmail());

        invoice.setData(flowRequestBean.getData());


        return invoice;

    }
}
