package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Model.TransactionFlowRequestBean;
import com.billt.core.invoicereceiver.Service.IRequestMapperService;
import org.springframework.stereotype.Service;


@Service("requestmappperservice")
public class RequestMapperServiceImpl implements IRequestMapperService {

    public TransactionFlowRequestBean MapToTransactionFlowBean(InvoiceRequestBean invoiceRequestBean){

        TransactionFlowRequestBean transactionFlowRequestBean = new TransactionFlowRequestBean();

        transactionFlowRequestBean.setMid(invoiceRequestBean.getMid());
        transactionFlowRequestBean.setVid(invoiceRequestBean.getVid());
        transactionFlowRequestBean.setOrderId(invoiceRequestBean.getOrderId());
        if(invoiceRequestBean.getEmail() != null){
            transactionFlowRequestBean.setEmail(invoiceRequestBean.getEmail());
        }
        if(invoiceRequestBean.getMobileNo()!= null){
            transactionFlowRequestBean.setMobileNo(invoiceRequestBean.getMobileNo());
        }

        if(invoiceRequestBean.getCustId()!= null){
            transactionFlowRequestBean.setCustId(invoiceRequestBean.getCustId());
        }

        transactionFlowRequestBean.setData(invoiceRequestBean.getData());
        transactionFlowRequestBean.setTransID("123y34747464362");

        return transactionFlowRequestBean;
    }
}
