package com.billt.core.datasourcebase.collection;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user_invoices")
@Data
public class Invoice {

    private String cid;
    private String mid;
    private String orderId;
    private String vid;

    @Indexed(unique = true)
    private String transID;

    private String mobile;
    private String email;
    private Object data;
    private Date date;

}
