package com.billt.core.invoicereceiver.Constants;

public class InvoiceReceiverConstants {

    //request parameters
    public static final String MID = "mid";
    public static final String VID = "vid";
    public static final String ORDER_ID = "orderId";
    public static final String CUST_ID = "billtId";
    public static final String TXN_ID = "txn_id";
    public static final String CHECKSUMHASH = "checksumhash";
    public static final String MOBILE_NO = "mobileNo";
    public static final String EMAIL = "email";
    public static final String MERCHANT_NAME = "merchantName";
    public static final String ADDRESS = "address";
    public static final String PHONE_NO_LIST = "phoneNoList";
    public static final String GST = "gst";
    public static final String TIN_NO = "tinNo";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String ITEM_LIST = "itemListWrapper";
    public static final String TOTAL_AMT = "totalAmt";
    public static final String VAT = "vat";
    public static final String NET = "net";
    public static final String CGST = "cgst";
    public static final String SGST = "sgst";
    public static final String DISCOUNT = "discount";
    public static final String CUSTOMER_MOBILE_NO = "customerMobileNO";
    public static final String CUSTOMER_EMAIL_TO = "customerEmail";
    public static final String TABLE_NO = "tableNO";
    public static final String HOST = "host";


    //validation parameters
    public static final Integer MID_LENGTH = 13;
    public static final Integer VID_LENGTH = 11;
    public static final Integer CUSTOMER_ID_MAX_LENGTH = 10;

    public static final String VALID_EMAIL_ADDRESS_REGEX =
            "[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+";

}
