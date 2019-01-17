package com.billt.core.datasourcebase.model.invoiceReceiver;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

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

    @NotBlank
    private Object data;

    @NotBlank
    private Date date;

    @Length(min = 0, max = 64)
    private String transID;
}
