package com.billt.core.datasourcebase.model.invoiceReceiver;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Data
public class TransactionFlowRequestBean {

    @NotBlank
    @Length(max = 50)
    private String mid;
    private String orderId;
    private String vid;

    @Length(max = 10)
    private String mobileNo;

    @Length(max=10)
    private String cid;

    @Length(max = 100)
    private String email;

    private String merchantName;

    private String address;

    private String phoneNo;

    private String gst;

    private String tin;

    private String subTotal;

    private String discount;

    private String cgst;

    private String sgst;

    private String serviceCharge;

    private String vat;

    private String time;

    private String date;

    private List<InvoiceItem> invoiceItems;

    private String totalAmt;

    private String net;

    @NotBlank
    private Date billtDate;

    @Length(min = 0, max = 64)
    private String transID;
}
