package com.buiit.app.invoicegenerator.utils;

public class ResponseCodeConstant{

    public static final String TRANSACTION_SUCCESS = "01";

    public static final String TRANSACTION_TIMEOUT = "1101";
    // Failure for fraud control
    public static final String SUSPICIUSS = "100";

    // ValidationErrors
    public static final String INVALID_JSON_REQUEST = "301";
    public static final String INVALID_REQUEST_TYPE = "302";
    public static final String BLANK_MERCHANT_ID = "303";
    public static final String BLANK_APP_IP = "304";
    public static final String MER_NOT_REG = "305";
    public static final String MER_EXP = "306";
    public static final String KEY_EXP = "307";
    public static final String INVALID_CUSTID = "318";
    public static final String DUPILCATE_ORDER_ID = "325";
    public static final String INVALID_CHECKSUM = "330";
    public static final String INVALID_TOKEN = "341";
    public static final String SYSTEM_ERROR = "501";
    public static final String JSON_ENCODING_ERROR = "502";

}
