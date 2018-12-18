package com.billt.core.invoicereceiver.Controllers;


import com.billt.core.invoicereceiver.Model.Invoice;
import com.billt.core.invoicereceiver.Service.UserDataFetchService;
import com.billt.core.invoicereceiver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/Mobile/")
public class ProfileController {
    @Autowired
    UserDataFetchService userDataFetchService;

  @GetMapping("profile")
  @ResponseBody
    public List<Invoice>  FetchuserInvoices(@RequestParam("id") String id ){
        System.out.println("Profile fetch for id = "+ id);

       List<Invoice> invoices= userDataFetchService.fetchInvoice(id);
       return invoices;
     /* ResponseEntity<List<Invoice>> entity = new ResponseEntity<List<Invoice>>(HttpStatus.CREATED);
      response.getOutputStream().write(finalResponse.getBytes());g
      response.setContentType();*/
    }

}
