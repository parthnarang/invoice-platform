package com.billt.core.invoicereceiver.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletRequest;

import static com.billt.core.invoicereceiver.Constants.InvoiceReceiverConstants.*;

/**
 * @author parth narang
 */

@JsonIgnoreProperties
public class InvoiceRequestBean {

    private transient HttpServletRequest request;

    @JsonProperty(MID)
    private String mid;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    @JsonProperty(VID)
    private String vid;

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getMid() {
        return mid;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustId() {
        return custId;
    }

    public String getChecksumhash() {
        return checksumhash;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    @JsonProperty(ORDER_ID)
    private String orderId;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setChecksumhash(String checksumhash) {
        this.checksumhash = checksumhash;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(CUST_ID)
    private String custId;
    @JsonProperty(CHECKSUMHASH)
    private String checksumhash;
    @JsonProperty(MOBILE_NO)
    private String mobileNo;
    @JsonProperty(EMAIL)
    private String email;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InvoiceRequestBean{" +
                "request=" + request +
                ", mid='" + mid + '\'' +
                ", vid='" + vid + '\'' +
                ", orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", checksumhash='" + checksumhash + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", data=" + data +
                '}';
    }

    @JsonProperty(DATA)
    private Object data;


    public InvoiceRequestBean(final HttpServletRequest request) {
        this.request = request;
        this.vid = vid;
        this.mid = request.getParameter(MID);
        this.orderId = request.getParameter(ORDER_ID);
        this.custId = request.getParameter(CUST_ID);
        this.checksumhash = request.getParameter(CHECKSUMHASH);
        this.mobileNo = request.getParameter(MOBILE_NO);
        this.email = request.getParameter(EMAIL);
        this.data = request.getParameter(DATA);
    }

}


