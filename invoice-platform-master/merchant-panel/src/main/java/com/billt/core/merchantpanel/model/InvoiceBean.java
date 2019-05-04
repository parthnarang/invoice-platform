package com.billt.core.merchantpanel.model;

import com.billt.core.datasourcebase.model.invoiceReceiver.InvoiceItem;
import lombok.Data;

@Data
public class InvoiceBean{

    private String mid;
    private String orderId;
    private String billtId;
    private String host;
    private int tableNo;
    private String checksumhash;
    private String mobileNo;
    private String email;
    private String merchantName;
    private String address;
    private String phoneNoList;
    private String gst;
    private String tinNo;
    private String time;
    private String date;
    private InvoiceItem[] invoiceItems;
    private double totalAmt;
    private double vat;
    private double net;
    private double cgst;
    private double discount;
    private double sgst;


}
