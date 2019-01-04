package com.billt.core.invoicereceiver.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

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
    @JsonProperty(DATA)
    private Object data;


    public InvoiceRequestBean(final JSONObject request) {
        this.request = request;
        try{
            this.vid = request.getString(VID);
            this.mid = request.getString(MID);
            this.orderId = request.getString(ORDER_ID);
            this.billtId = request.getString(CUST_ID);
            this.checksumhash = request.getString(CHECKSUMHASH);
            this.email = request.getString(EMAIL);
            this.data = request.getJSONObject(DATA);
            this.mobileNo = request.getString(MOBILE_NO);}
        catch (JSONException e){
            System.out.println(e.toString());
        }
    }
    public InvoiceRequestBean(){

    }

}


