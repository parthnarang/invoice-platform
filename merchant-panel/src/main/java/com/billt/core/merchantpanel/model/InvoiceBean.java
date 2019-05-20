package com.billt.core.merchantpanel.model;

import com.billt.core.datasourcebase.model.ItemListWrapper;
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
    private ItemListWrapper itemListWrapper;
    private Double totalAmt;
    private Double vat;
    private Double net;
    private Double cgst;
    private Double discount;
    private Double sgst;
    private String customerEmail;
    private String customerMobileNO;


}
