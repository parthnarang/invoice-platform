package com.billt.core.notificationservice.Models;

import javax.annotation.Generated;

import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationRequestModel {

    @SerializedName("data")
    private TransactionFlowRequestBean transactionFlowRequestBean;
    @SerializedName("to")
    private String mTo;

    public TransactionFlowRequestBean getData() {
        return this.transactionFlowRequestBean;
    }

    public void setData(TransactionFlowRequestBean transactionFlowRequestBean) {
        this.transactionFlowRequestBean = transactionFlowRequestBean;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }
}