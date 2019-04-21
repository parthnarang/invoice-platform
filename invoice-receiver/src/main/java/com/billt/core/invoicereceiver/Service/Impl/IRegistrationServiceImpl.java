package com.billt.core.invoicereceiver.Service.Impl;

import com.billt.core.datasourcebase.Service.ICustomerTokenService;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.entities.jpa.CustomerToken;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.read.CustomerTokenReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.CustomerWriteRepository;
import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;
import com.billt.core.invoicereceiver.Service.RegistrationService;
import com.billt.core.invoicereceiver.utils.Response;
import com.google.firebase.auth.FirebaseToken;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service("registrationTokenService")
public class IRegistrationServiceImpl implements RegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IRegistrationServiceImpl.class);

    @Autowired
    ICustomerTokenService iCustomerTokenService;

    @Autowired
    CustomerTokenReadRepository customerTokenReadRepository;

    @Autowired
    CustomerReadRepository customerReadRepository;

    @Autowired
    CustomerWriteRepository customerWriteRepository;

    @Override
    public Response tokenVerification(RegistrationRequestHeader header, RegistrationRequestBody body){
        FirebaseToken decodedToken = null;
        String idToken = header.getToken();
        Response response = new Response();
        try {
            //decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            //String uid = decodedToken.getUid();
            String uid = "123";
            if(uid!=null){
                String cid = createCustomerId(body.getFirstName());
                while(customerTokenReadRepository.findCustomerTokenByCid(cid)!=null){
                    cid = createCustomerId(body.getFirstName());
                }
                Customer customer = new Customer();
                customer.setCid(cid);
                customer.setCreatedOn(new Date());
                customer.setUpdatedOn(new Date());
                customer.setFirstName(body.getFirstName());
                customer.setLastName(body.getLastName());
                customer.setMobile(body.getPhoneNo());
                customer.setEmail(body.getEmail());
                customerWriteRepository.save(customer);

                CustomerToken customerToken = new CustomerToken();
                customerToken.setToken(idToken);
                customerToken.setCreatedOn(new Date());
                customerToken.setUpdatedOn(new Date());
                customerToken.setCid(cid);
                iCustomerTokenService.registerCustomerToken(customerToken);

                response.setCode(104);
                response.setStatus(true);
                response.setMessage("Token Validated.");
                return response;
            }
            else{
                response.setCode(103);
                response.setStatus(false);
                response.setMessage("Invalid User Token.");
                return response;
            }
        } catch (/*FirebaseAuth*/Exception e) {
            e.printStackTrace();
            response.setCode(105);
            response.setStatus(false);
            response.setMessage("Internal Server Error.");
            return response;
        }

    }

    private String createCustomerId(String firstName) {

        String generatedString = generatingRandomAlphanumericString();
        String cid = firstName + generatedString;
        return cid;
    }

    private String generatingRandomAlphanumericString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(4);
        return generatedString;
    }

    @Override
    public Response phoneNumberVerification(String phoneNum){
        Response response = new Response();
        try{
            Customer customer = customerReadRepository.findCustomerByMobile(phoneNum);
            if(customer==null){
                response.setCode(101);
                response.setStatus(false);
                response.setMessage("No such phone number exists.");
                return response;
            }
            else{
                response.setCode(102);
                response.setStatus(true);
                response.setMessage("Entry found.");
                return response;
            }

        }catch(Exception  e){
            e.printStackTrace();
            response.setCode(105);
            response.setStatus(false);
            response.setMessage("Internal Server Error.");
            return response;
        }

    }

}
