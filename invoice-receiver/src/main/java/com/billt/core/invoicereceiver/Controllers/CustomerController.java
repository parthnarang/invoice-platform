package com.billt.core.invoicereceiver.Controllers;

import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.Customer;
import com.billt.core.datasourcebase.repositories.mongo.read.InvoiceReadRepository;
import com.billt.core.invoicereceiver.Model.ResponseMessage;
import com.billt.core.invoicereceiver.Model.User;
import com.billt.core.invoicereceiver.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Mobile/")
public class CustomerController {

    @Autowired
    InvoiceReadRepository invoiceReadRepository;

    @Autowired
    @Qualifier(value="customerService")
    ICustomerService iCustomerService;



    @GetMapping("rest/fetchCustomerInvoices")
    @ResponseBody
    public List<Invoice> findCustomerInvoices(@RequestParam("cid") String cid) throws IOException, ServletException {
        List<Invoice> invoices = invoiceReadRepository.findByCid(cid);

        return invoiceReadRepository.findByCid(cid);
    }

    @RequestMapping(value= {"signup"}, method=RequestMethod.POST)
    @ResponseBody
    public ResponseMessage createUser(@RequestBody @Valid Customer customer) {

        ResponseMessage responseMessage = new ResponseMessage(true);
        try {
            Customer customerExist = iCustomerService.checkIfCustomerExist(customer.getMobile(), customer.getEmail());

            if (customerExist != null) {
                responseMessage.setMessage("user already exist!");
            } else {
                iCustomerService.saveCustomer(customer);
                responseMessage.setMessage("successfully registered!");
            }
        } catch (Exception exception) {
            responseMessage.setSuccessful(false);
            responseMessage.setMessage("internal server error, please try again later !!");
        }
        finally {
            return responseMessage;
        }
    }

    @PostMapping("CheckPhoneNo")
    public ResponseMessage CheckPhoneNo(@RequestParam("phoneno") String phoneNo) {
        System.out.println(phoneNo);
        ResponseMessage responseMessage = new ResponseMessage(true);
        try {
            Customer customerExist = iCustomerService.checkIfCustomerExist(phoneNo, null);

            if (customerExist != null) {
                responseMessage.setStatus(true);
                responseMessage.setMessage("user already exist!");
            } else {
                responseMessage.setStatus(false);
                responseMessage.setMessage("user does not exit ! redirect to signup page");
            }
        } catch (Exception exception) {
            responseMessage.setSuccessful(false);
            responseMessage.setMessage("internal server error, please try again later !!");
        }
        finally {
            return responseMessage;
        }

    }

}
