package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.invoicereceiver.Constants.InvoiceReceiverConstants;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Service.IValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;


@Service("validationservice")

public class ValidationServiceImpl implements IValidationService {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationServiceImpl.class);

    public boolean checksumValidatation(final InvoiceRequestBean requestData){
        //sample checksum validation code
        return true;

    }

    public boolean validateInvoiceData(final InvoiceRequestBean requestData){


        if (!validateMid(requestData.getMid()) || !validateVid(requestData.getVid()) || !validateOrderId(requestData.getOrderId())
                || !validateCustId(requestData.getCustId(),requestData.getEmail(),requestData.getMobileNo())){
            return false;
        }
        return true;
    }

    private boolean validateMid(String mid) {

        if (StringUtils.isEmpty(mid) || mid.length() > InvoiceReceiverConstants.MID_LENGTH || mid.length() > InvoiceReceiverConstants.MID_LENGTH) {
            LOG.error("Invalid MID ::{}", mid);
            return false;
        }
        return true;
    }

    private boolean validateVid(String vid) {

        if (StringUtils.isEmpty(vid) || vid.length() > InvoiceReceiverConstants.VID_LENGTH || vid.length() > InvoiceReceiverConstants.MID_LENGTH) {
            LOG.error("Invalid VID ::{}", vid);
            return false;
        }
        return true;
    }
    private boolean validateOrderId(String orderID) {

        if (StringUtils.isEmpty(orderID)){
            LOG.error("no  orderID ::{}", orderID);
            return false;
        }

        return true;
    }

    private boolean validateCustId(String custID,String mobile,String email) {

        if (StringUtils.isEmpty(custID) || custID.length() > InvoiceReceiverConstants.CUSTOMER_ID_MAX_LENGTH) {
            LOG.error("Invalid custID ::{}", custID);
            if(validateEmail(email))
                return true;
            if(validateMobile(mobile))
                return true;
            return false;
        }

        return true;
    }

    private boolean validateMobile(String mobile) {

        if (StringUtils.isEmpty(mobile) || mobile.length() != 10 ) {
            LOG.error("Invalid mobile ::{}", mobile);
            return false;
        }

        return true;
    }

    private boolean validateEmail(String email) {

        if (StringUtils.isEmpty(email) || !Pattern.matches(InvoiceReceiverConstants.VALID_EMAIL_ADDRESS_REGEX, email)) {
            LOG.error("Invalid email ::{}", email);
            return false;
        }

        return true;
    }
}

