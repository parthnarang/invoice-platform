package com.billt.core.merchantpanel.service;

import com.billt.core.datasourcebase.Service.IUrlMapperService;
import com.billt.core.datasourcebase.Service.Impl.IUrlMapperImpl;
import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.InvoiceUrl;
import com.billt.core.datasourcebase.repositories.mongo.read.InvoiceReadRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.billt.core.merchantpanel.Constants.MerchantPanelConstants.*;

@Service
public class SmsInvoiceService {

    @Autowired
    @Qualifier(value="urlService")
    IUrlMapperService urlMapperService;

    @Autowired
    InvoiceReadRepository invoiceReadRepository;

    private static final Logger LOG = LoggerFactory.getLogger(SmsInvoiceService.class);

    @Autowired
    @Qualifier("merchantfreemarkerhelper")
    private Configuration freemarkerConfig;

    public String constructSmsInvoice(String id){
        try {
            String curl = BILLT_URL;
            InvoiceUrl invoiceUrl = urlMapperService.fetchTransaction(curl + id);
            String transactionId = invoiceUrl.getTransactionId();
            Invoice invoice = invoiceReadRepository.findByTransID(transactionId);

            final Map smsInvoiceModel = new HashMap<String,String>();
            smsInvoiceModel.put(MERCHANT_NAME, invoice.getMerchantName());
            smsInvoiceModel.put(MERCHANT_ADDR, invoice.getAddress());

            smsInvoiceModel.put(TXN_CGST, invoice.getCgst());
            smsInvoiceModel.put(TXN_DATE, invoice.getDate());
            smsInvoiceModel.put(TXN_DISCOUNT, invoice.getDiscount());
            smsInvoiceModel.put(TXN_GST, invoice.getGst());
            smsInvoiceModel.put(TXN_ITEMS, invoice.getInvoiceItems());
            //smsInvoiceModel.put(TXN_PHNO, invoice.getMobileNo());
            smsInvoiceModel.put(TXN_NET, invoice.getNet());
            smsInvoiceModel.put(TXN_ORDER_ID, invoice.getOrderId());
            smsInvoiceModel.put(TXN_SERVICE_CHARGE, invoice.getServiceCharge());
            smsInvoiceModel.put(TXN_SGST, invoice.getSgst());
            //smsInvoiceModel.put(TXN_SUBTOTAL, invoice.getSubTotal());
            smsInvoiceModel.put(TXN_TIN, invoice.getTin());
            smsInvoiceModel.put(TXN_TOTAL, invoice.getTotalAmt());
            smsInvoiceModel.put(TXN_ID, invoice.getTransID());
            smsInvoiceModel.put(TXN_VAT, invoice.getVat());


            Template t = freemarkerConfig.getTemplate("SmsFreeMarkerTemplate.ftl");
            String smsInvoice = FreeMarkerTemplateUtils.processTemplateIntoString(t,smsInvoiceModel);
            LOG.info("Sms Invoice Message " + smsInvoice);
            return smsInvoice;

        } catch (IOException e) {
            LOG.info("Email Sender freemarker ioexception = " + e.toString());
        } catch (TemplateException e) {
            LOG.info("Email Sender freemarker templateexception = " + e.toString());
        }

        return "";
    }
}
