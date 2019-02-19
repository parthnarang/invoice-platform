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
    private String orderId;
    @JsonProperty(CUST_ID)
    private String billtId;
    @JsonProperty(CHECKSUMHASH)
    private String checksumhash;
    @JsonProperty(EMAIL)
    private String email;
    @JsonProperty(MOBILE_NO)
    private String mobileNo;
    @JsonProperty(GST)
    private String gst;
    @JsonProperty(CGST)
    private String cgst;
    @JsonProperty(SGST)
    private String sgst;
    @JsonProperty(TIN)
    private String tin;
    @JsonProperty(DISCOUNT)
    private String discount;
    @JsonProperty(SERVICE_CHARGE)
    private String serviceCharge;
    @JsonProperty(SUBTOTAL)
    private String subtotal;
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


