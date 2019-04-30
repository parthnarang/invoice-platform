package com.billt.core.merchantpanel.controller;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.datasourcebase.services.FileStorageServiceImpl;
import com.billt.core.invoicereceiver.Model.BillTGenericRequest;
import com.billt.core.invoicereceiver.Model.RegistrationRequestBody;
import com.billt.core.invoicereceiver.Model.RegistrationRequestHeader;
import com.billt.core.invoicereceiver.Model.RegistrationRequestPayload;
import com.billt.core.merchantpanel.model.InvoiceBean;
import com.billt.core.merchantpanel.service.Impl.InvoiceService;
import com.billt.core.merchantpanel.service.Impl.SecurityServiceImpl;
import com.billt.core.merchantpanel.service.MerchantService;
import com.billt.core.merchantpanel.validator.MerchantValidator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

//import com.billt.core.datasourcebase.services.MerchantService;

@Controller
@RequestMapping("/panel/")
public class MerchantController {

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    MerchantValidator merchantValidator;


    @Autowired
    private FileStorageServiceImpl fileStorageService;

    @Autowired
    private MerchantReadRepository merchantReadRepository;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("con", "dashboard");


        return "layout";
    }
    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("merchant",new Merchant());
        return "login";
    }


    @GetMapping("registration")
    public String signup(Model model) {
        Merchant merchant = new Merchant();
        model.addAttribute("merchant",merchant);
        return "signup";
    }

    @PostMapping("registration")
    public String addNewMerchant(@Valid Merchant merchant, BindingResult bindingResult) {
/*        merchantValidator.validate(merchant,bindingResult);

        if(bindingResult.hasErrors())
            return "signup";*/

        log.info("Receiving request to add new merchant {}",merchant);
        merchantService.addNewMerchant(merchant);
        log.info("Creation for request is done");
        securityService.autoLogin(merchant.getEmail(), merchant.getPasswordConfirm());
        return "redirect:/panel/home";
    }


    @GetMapping("invoice")
    public String invoice(Model model) {

         invoiceService.getNewInvoice(model);

        return "layout";
    }

    @PostMapping("postNewInvoice")
    public String postInvoice( @ModelAttribute InvoiceBean invoice) {

        invoiceService.sendNewInvoice(invoice);

        return "redirect:/panel/home";
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        try {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;

            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());


        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (IOException ex) {
            log.info("Could not determine file type.");
            return null;

        }
    }

    @ResponseBody
    @PostMapping(value = "merchantDetails", consumes = "application/json")
    public String getMerchantDetails(final @RequestBody BillTGenericRequest<RegistrationRequestPayload<RegistrationRequestHeader, RegistrationRequestBody>> payload) {
        final RegistrationRequestHeader header = payload.getRequest().getHead();
        final RegistrationRequestBody body = payload.getRequest().getBody();
        log.info("GetMerchantDetails Post Mapping");
        System.out.println("GetMerchantDetails Post Mapping println");
        return merchantService.findMerchantDetails(header,body);
        return "";
    }






}
