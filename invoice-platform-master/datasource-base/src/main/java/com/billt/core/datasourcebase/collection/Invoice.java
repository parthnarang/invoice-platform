package com.billt.core.datasourcebase.collection;

import com.billt.core.datasourcebase.model.invoiceReceiver.InvoiceItem;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "user_invoices")
@Data
public class Invoice {

    private String cid;
    private String mid;
    private String orderId;
    private String vid;

    @Indexed(unique = true)
    private String transID;

    private String mobile;
    private String email;

    private String merchantName;

    private String address;

    private String phoneNo;

    private String gst;

    private String time;

    private String date;

    private String cgst;

    private String sgst;

    private String discount;

    private String serviceCharge;

    private String tin;

    private List<InvoiceItem> invoiceItems;

    private String totalAmt;

    private String vat;


    private String net;

    private Date billtDate;



}
