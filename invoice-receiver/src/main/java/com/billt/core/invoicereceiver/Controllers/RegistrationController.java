package com.billt.core.invoicereceiver.Controllers;

import com.billt.core.invoicereceiver.Model.*;
import com.billt.core.invoicereceiver.utils.Response;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/invoicereceiver/registration/")
public class RegistrationController {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
    @PostMapping(value = "signupUser", consumes = "application/json")
    public Response RegistrationStatus(
            //@ApiParam(value = "Json payload containing RegistrationRequestHeader and RegistrationRequestBody", required = true)
            final @RequestBody BillTGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload)
            throws UnrecognizedPropertyException {
        LOG.info("Registration Post Mapping");
        System.out.println("Registration Post Mapping println");

        Long startTime = System.currentTimeMillis();
        try {
            LOG.info("Registration head start ");
            final RegistrationRequestHeader header = payload.getRequest().getHead();
            LOG.info("Registration head end ");

            LOG.info("Registration body start ");

            final RegistrationRequestBody body = payload.getRequest().getBody();
            LOG.info("Registration body end ");
            LOG.info("Registration body is : " + body.getFirstName());


        } catch (Exception e) {
            LOG.info("Registration Request Error : ", e.getMessage());
        }

        return null;
    }
}