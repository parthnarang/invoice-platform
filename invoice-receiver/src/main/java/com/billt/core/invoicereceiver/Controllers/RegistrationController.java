package com.billt.core.invoicereceiver.Controllers;

import com.billt.core.invoicereceiver.Model.BilltGenericRequest;
import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;
import com.billt.core.invoicereceiver.Model.RegistrationRequestPayload;
import com.billt.core.invoicereceiver.service.RegistrationService;
import com.billt.core.invoicereceiver.utils.Response;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;


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
            final @RequestBody BilltGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload) {
        LOG.info("Registration Post Mapping");
        System.out.println("Registration Post Mapping println");

        Long startTime = System.currentTimeMillis();
        try {
            System.out.println("Registration Status try block");
            final RegistrationRequestHeader header = payload.getRequest().getHead();
            System.out.println("Registration Status header done");
            final RegistrationRequestBody body = payload.getRequest().getBody();
            LOG.info("Registration body is : " + body.getFirstName());
            System.out.println("Registration Status body done");
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
    public Response validatePhoneNumber(final @RequestBody BilltGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload){
        final RegistrationRequestHeader header = payload.getRequest().getHead();
        final RegistrationRequestBody body = payload.getRequest().getBody();
        LOG.info("CheckPhoneNumber Post Mapping");
        System.out.println("CheckPhoneNumber Post Mapping println");
        return registrationService.phoneNumberVerification(body.getPhoneNo());
    }

    @ResponseBody
    @PostMapping(value = "updateToken", consumes = "application/json")
    public Response updateToken(final @RequestBody BilltGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload){
        final RegistrationRequestHeader header = payload.getRequest().getHead();
        final RegistrationRequestBody body = payload.getRequest().getBody();
        LOG.info("Update Token Post Mapping");
        System.out.println("Update Token Post Mapping println");
        return registrationService.updateCustomerToken(header,body);
    }

    @ResponseBody
    @GetMapping(value = "getFeedback", produces = "application/json")
    public String getFeedback(){
        try {

            //File file = ResourceUtils.getFile("classpath:resources/feedback");
            File file = new File(
                    getClass().getClassLoader().getResource("Feedback.txt").getFile()
            );
            InputStream inputStream = new FileInputStream(file);
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            String resultString = writer.toString();
            System.out.println("Result String is: "+ resultString);
            return resultString;

        } catch (IOException e) {
            LOG.info("Feedback.txt Error is: " + e.getMessage());
        }
        return "Internal Server Error";
    }

    @ResponseBody
    @GetMapping(value = "getPrivacyPolicy", produces = "application/json")
    public String getPrivacyPolicy(){
        try {
            File file = new File(
                    getClass().getClassLoader().getResource("PrivacyPolicy.txt").getFile()
            );
            InputStream inputStream = new FileInputStream(file);
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            String resultString = writer.toString();
            System.out.println("Result String is: "+ resultString);
            return resultString;

        } catch (IOException e) {
            LOG.info("PrivacyPolicy.txt Error is: " + e.getMessage());
        }
        return "Internal Server Error";
    }

    @ResponseBody
    @GetMapping(value = "getAbout", produces = "application/json")
    public String getAbout(){
        try {
            File file = new File(
                    getClass().getClassLoader().getResource("About.txt").getFile()
            );
            InputStream inputStream = new FileInputStream(file);
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            String resultString = writer.toString();
            System.out.println("Result String is: "+ resultString);
            return resultString;

        } catch (IOException e) {
            LOG.info("About.txt Error is: " + e.getMessage());
        }
        return "Internal Server Error";
    }







}