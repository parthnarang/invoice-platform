package com.billt.core.invoicereceiver.Constants;

public class InvoiceReceiverConstants {

    //request parameters
    public static final String MID = "mid";
    public static final String VID = "vid";
    public static final String ORDER_ID = "order_id";
    public static final String CUST_ID = "cust_id";
    public static final String TXN_ID = "txn_id";
    public static final String CHECKSUMHASH = "checksumhash";
    public static final String MOBILE_NO = "mobile";
    public static final String EMAIL = "email";
    public static final String DATA = "data";


    //validation parameters
    public static final Integer MID_LENGTH = 13;
    public static final Integer VID_LENGTH = 11;
    public static final Integer CUSTOMER_ID_MAX_LENGTH = 10;

    public static final String VALID_EMAIL_ADDRESS_REGEX =
            "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+";

}
