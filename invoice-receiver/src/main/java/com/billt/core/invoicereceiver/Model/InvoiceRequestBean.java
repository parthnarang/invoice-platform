package com.billt.core.invoicereceiver.Model;


import com.billt.core.datasourcebase.model.ItemListWrapper;
import com.billt.core.datasourcebase.model.invoiceReceiver.InvoiceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.JSONObject;

import java.util.List;

import static com.billt.core.invoicereceiver.Constants.InvoiceReceiverConstants.*;

/**
 * @author parth narang
 */

@JsonIgnoreProperties
@Data
public class InvoiceRequestBean {

    private transient JSONObject request;
    private String mid;
    private String vid;
    private String orderId;
    private String billtId;
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


