package com.billt.core.invoicereceiver.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Document(collection = "user_invoices")
public class Invoice {

    String customerId;
    String merchantId;
    String merchantLogo;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    String invoiceId;
    Object data;

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    String dateAndTime;

    public String getCustomerId(){
        return customerId;
    }
    public void setCustomerId(String CustomerId){
        this.customerId=CustomerId;
    }

    public String getMerchantId(){
        return merchantId;
    }
    public void setMerchantId(String merchantId){
        this.merchantId=merchantId;
    }

    public void setMerchantLogo(String merchantLogo){
        this.merchantLogo=merchantLogo;
    }
    public String getMerchantLogo(){
        return merchantLogo;
    }

    public void setData(Object jsonobj){
        this.data = jsonobj;
    }
    public Object getData(){
        return data;
    }

    @Override
    public String toString() {
        return "merchantid= "+merchantId +"customerid= " +customerId +"data= " +data;
    }
}
