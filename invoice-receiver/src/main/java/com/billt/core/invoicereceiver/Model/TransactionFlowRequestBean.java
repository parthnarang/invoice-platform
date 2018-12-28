package com.billt.core.invoicereceiver.Model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class TransactionFlowRequestBean {

    public String getMid() {
        return mid;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getVid() {
        return vid;
    }

    public String getCustId() {
        return custId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public Object getData() {
        return data;
    }

    public String getTransID() {
        return transID;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    @NotBlank
    @Length(max = 50)
    private String mid;
    private String orderId;
    private String vid;

    @Length(max = 10)
    private String custId;
    private String mobileNo;

    @Length(max = 100)
    private String email;

    @NotBlank
    private Object data;

    @Length(min = 0, max = 64)
    private String transID;


}
