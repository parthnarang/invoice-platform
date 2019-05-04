package com.billt.core.invoicereceiver.Controllers;

import com.billt.core.invoicereceiver.Model.*;
import com.billt.core.invoicereceiver.Service.RegistrationService;
import com.billt.core.invoicereceiver.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/invoicereceiver/registration/")
public class RegistrationController {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    RegistrationService registrationService;

    @ResponseBody
    @PostMapping(value = "signupUser", consumes = "application/json")
    public Response registrationStatus(
            //@ApiParam(value = "Json payload containing RegistrationRequestHeader and RegistrationRequestBody", required = true)
            final @RequestBody BillTGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload) {
        LOG.info("Registration Post Mapping");
        System.out.println("Registration Post Mapping println");

        Long startTime = System.currentTimeMillis();
        try {
            final RegistrationRequestHeader header = payload.getRequest().getHead();

            final RegistrationRequestBody body = payload.getRequest().getBody();
            LOG.info("Registration body is : " + body.getFirstName());
            return registrationService.tokenVerification(header, body);
        } catch (Exception e) {
            LOG.info("Registration Request Error : ", e.getMessage());

            Response response = new Response();
            response.setCode(105);
            response.setStatus(false);
            response.setMessage("Internal Server Error.");
            return response;
        }
    }

    @ResponseBody
    @PostMapping(value = "checkPhoneNum", consumes = "application/json")
    public Response validatePhoneNumber(final @RequestBody BillTGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload){
        final RegistrationRequestHeader header = payload.getRequest().getHead();
        final RegistrationRequestBody body = payload.getRequest().getBody();
        LOG.info("CheckPhoneNumber Post Mapping");
        System.out.println("CheckPhoneNumber Post Mapping println");
        return registrationService.phoneNumberVerification(body.getPhoneNo());
    }
}