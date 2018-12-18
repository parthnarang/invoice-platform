package com.billt.core.invoicereceiver.utils;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class Response {

    private Boolean status;
    private Integer code;
    private String message;
    private  String content;

    public Response(){
        status = true;
        code = 100;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content= content;
    }
}

