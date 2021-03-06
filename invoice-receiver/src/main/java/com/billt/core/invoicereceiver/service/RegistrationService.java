package com.billt.core.invoicereceiver.service;

import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;
import com.billt.core.invoicereceiver.utils.Response;

public interface RegistrationService {
    public Response phoneNumberVerification(String phoneNum);
    public Response tokenVerification(RegistrationRequestHeader header, RegistrationRequestBody body);

    Response updateCustomerToken(RegistrationRequestHeader header, RegistrationRequestBody body);
}
