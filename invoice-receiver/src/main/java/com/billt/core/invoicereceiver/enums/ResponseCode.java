package com.billt.core.invoicereceiver.enums;

public enum ResponseCode {

    TRANSACTION_SUCCESS(100," invoice is successfully processed",true),INVALID_REQUEST(101,"request is invalid",false),
    INVALID_MID(106,"mid is not authorized",false),
    CHECKSUM_MISMATCH(101,"checksum send is not valid",false),INTERNAL_SERVER_ERROR(105,"some internal server error occured",
            false);

    private String code;
    private String message;
    private boolean status;

    ResponseCode(int code, String message,Boolean status) {
        this.code = String.valueOf(code);
        this.message = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
