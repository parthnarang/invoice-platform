package com.billt.core.invoicereceiver.enums;

public class MerchantRequest {

    String customerId;
    String orderId;
    String merchantId;
    String mobileNo;
    String email;
    String invoiceId;

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }


    public String getInvoiceId() {
        return invoiceId;
    }

    Object data;


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String CustomerId) {
        this.customerId = CustomerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }


    public void setData(Object jsonobj) {
        this.data = jsonobj;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "merchantid= " + merchantId + "customerid= " + customerId + "data= " + data;
    }
}




