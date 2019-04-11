package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.Service.IOrderService;
import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.entities.jpa.Orders;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Exceptions.RequestDataMappingException;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Service.ICustomerService;
import com.billt.core.invoicereceiver.Service.IRequestMapperService;
import com.billt.core.datasourcebase.Service.MerchantService;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;


@Service("requestmappperservice")
public class RequestMapperServiceImpl implements IRequestMapperService {

    private static final Logger LOG = LoggerFactory.getLogger(RequestMapperServiceImpl.class);

    @Autowired
    MerchantService merchantService;


   // InvoiceWriteRepository invoiceWriteRepository;

    @Autowired
    @Qualifier(value ="customerService")
    ICustomerService customerService;

    @Autowired
    @Qualifier(value = "orderService")
    IOrderService iOrderService;


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
        //transactionFlowRequestBean.setMerchantName(invoiceRequestBean.getMerchantName());
        //transactionFlowRequestBean.setAddress(invoiceRequestBean.getAddress());
        //transactionFlowRequestBean.setPhoneNo(invoiceRequestBean.getPhoneNo());
        transactionFlowRequestBean.setGst(invoiceRequestBean.getGst());
        transactionFlowRequestBean.setTime(invoiceRequestBean.getTime());
        transactionFlowRequestBean.setDate(invoiceRequestBean.getDate());
        transactionFlowRequestBean.setTotalAmt(invoiceRequestBean.getTotalAmt());
        transactionFlowRequestBean.setVat(invoiceRequestBean.getVat());
        transactionFlowRequestBean.setNet(invoiceRequestBean.getNet());
        transactionFlowRequestBean.setCgst(invoiceRequestBean.getCgst());
        transactionFlowRequestBean.setSgst(invoiceRequestBean.getSgst());
        transactionFlowRequestBean.setDiscount(invoiceRequestBean.getDiscount());
        transactionFlowRequestBean.setServiceCharge(invoiceRequestBean.getServiceCharge());
        transactionFlowRequestBean.setTin(invoiceRequestBean.getTin());
        transactionFlowRequestBean.setInvoiceItems(invoiceRequestBean.getInvoiceItems());

        if(customer!=null){
            transactionFlowRequestBean.setCid(customer.getCid());
        }

        if(invoiceRequestBean.getEmail() != null){
            transactionFlowRequestBean.setEmail(invoiceRequestBean.getEmail());
        }
        if(invoiceRequestBean.getMobileNo()!= null){
            transactionFlowRequestBean.setMobileNo(invoiceRequestBean.getMobileNo());
        }

        String transacId = constructTransactionId();

        transactionFlowRequestBean.setTransID(transacId);
        transactionFlowRequestBean.setBilltDate(new Date());

        //Invoice invoice = invoiceWriteRepository.findTopByOrderByCreatedDesc();
        /*String transId = "123";
        /*invoice.getTransID();
        long transacId = Long.parseLong(transId)+1;*/
        Orders orders = constructOrderEntry(transactionFlowRequestBean.getMid(),transactionFlowRequestBean.getOrderId(),transacId);
        iOrderService.registerOrder(orders);

        //transactionFlowRequestBean.setTransID("123y34747464362");


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
        invoice.setCgst(flowRequestBean.getCgst());
        invoice.setSgst(flowRequestBean.getSgst());
        invoice.setDiscount(flowRequestBean.getDiscount());
        invoice.setServiceCharge(flowRequestBean.getServiceCharge());
        invoice.setTin(flowRequestBean.getTin());
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

    private Orders constructOrderEntry(String mid, String orderId,String transacactionId){
        Orders orders = new Orders();
        orders.setCreatedOn(new Date());
        orders.setMid(mid);
        if(orderId != null){
            orders.setOrderId(orderId);
        }
        orders.setUpdatedOn(new Date());

        orders.setTransactionId(transacactionId);
        return orders;
    }

    private String constructTransactionId(){
        long transactionId = System.currentTimeMillis();
        Random random = new Random();
        int rval = random.nextInt(1000000) + 1000000;
        String result = String.valueOf(transactionId) + String.valueOf(rval);
        return result;
    }
}
