package com.billt.core.invoicereceiver.Model;


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
    @JsonProperty(MID)
    private String mid;
    @JsonProperty(VID)
    private String vid;
    @JsonProperty(ORDER_ID)
    private String orderId;
    @JsonProperty(CUST_ID)
    private String billtId;
    @JsonProperty(CHECKSUMHASH)
    private String checksumhash;
    @JsonProperty(MOBILE_NO)
    private String mobileNo;
    @JsonProperty(EMAIL)
    private String email;
    @JsonProperty(MERCHANT_NAME)
    private String merchantName;
    @JsonProperty(ADDRESS)
    private String address;
    @JsonProperty(PHONE_NO)
    private String phoneNo;
    @JsonProperty(GST)
    private String gst;
    @JsonProperty(TIME)
    private String time;
    @JsonProperty(DATE)
    private String date;
    @JsonProperty(ITEMS)
    private List<InvoiceItem> invoiceItems;
    @JsonProperty(TOTAL_AMT)
    private String totalAmt;
    @JsonProperty(VAT)
    private String vat;
    @JsonProperty(NET)
    private String net;
}


