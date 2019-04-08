package com.billt.core.notificationservice.Helpers;

import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import java.util.HashMap;
import java.util.Map;

import static com.constants.CommunicationConstants.*;


@Service
public class EmailHelper {


    @Autowired
    @Qualifier("freemarkerhelper")
    private Configuration freemarkerConfig;


    private static final Logger LOG = LoggerFactory.getLogger(EmailHelper.class);

    public String constructEmail(TransactionFlowRequestBean transactionFlowRequestBean) {
        //emailSender.sendSimpleMessage("norirahul@gmail.com","Test mail","Hello this is a test mail for BillT");
        try {
            final Map transactionModel = new HashMap<String,String>();
            transactionModel.put(MERCHANT_NAME, transactionFlowRequestBean.getMerchantName());
            transactionModel.put(MERCHANT_ADDR, transactionFlowRequestBean.getAddress());

            transactionModel.put(TXN_CGST, transactionFlowRequestBean.getCgst());
            transactionModel.put(TXN_DATE, transactionFlowRequestBean.getDate());
            transactionModel.put(TXN_DISCOUNT, transactionFlowRequestBean.getDiscount());
            transactionModel.put(TXN_GST, transactionFlowRequestBean.getGst());
            transactionModel.put(TXN_ITEMS, transactionFlowRequestBean.getInvoiceItems());
            transactionModel.put(TXN_PHNO, transactionFlowRequestBean.getMobileNo());
            transactionModel.put(TXN_NET, transactionFlowRequestBean.getNet());
            transactionModel.put(TXN_ORDER_ID, transactionFlowRequestBean.getOrderId());
            transactionModel.put(TXN_SERVICE_CHARGE, transactionFlowRequestBean.getServiceCharge());
            transactionModel.put(TXN_SGST, transactionFlowRequestBean.getSgst());
            transactionModel.put(TXN_SUBTOTAL, transactionFlowRequestBean.getSubTotal());
            transactionModel.put(TXN_TIN, transactionFlowRequestBean.getTin());
            transactionModel.put(TXN_TOTAL, transactionFlowRequestBean.getTotalAmt());
            transactionModel.put(TXN_ID, transactionFlowRequestBean.getTransID());
            transactionModel.put(TXN_VAT, transactionFlowRequestBean.getVat());

            Template t = freemarkerConfig.getTemplate("CustomerEmailInvoiceTemplate.ftl");
            String emailMessage = FreeMarkerTemplateUtils.processTemplateIntoString(t,transactionModel);


            LOG.info("Email Sender emailMessage " + emailMessage);

            return emailMessage;

        } catch (Exception exception) {
                LOG.info("Email Sender velocity context exception = " + exception.toString());
        }
        return "";
    }
}
