package com.billt.core.merchantpanel.controller;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.datasourcebase.services.FileStorageServiceImpl;
import com.billt.core.merchantpanel.model.InvoiceBean;
import com.billt.core.merchantpanel.service.DashboardService;
import com.billt.core.merchantpanel.service.Impl.InvoiceService;
import com.billt.core.merchantpanel.service.Impl.SecurityServiceImpl;
import com.billt.core.merchantpanel.service.MerchantServiceNew;
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
import java.util.List;
import java.util.Optional;

//import com.billt.core.datasourcebase.services.MerchantService;

@Controller
@RequestMapping("/panel/")
public class MerchantController {

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantServiceNew merchantServiceNew;

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

    @Autowired
    DashboardService dashboardService;

    @GetMapping("home")
    public String home(Model model, @RequestParam("dRange") Optional<String> dRange) {
        model.addAttribute("con", "dashboard");
        Merchant merchant = findLoggedInMerchant();
        String mid = merchant.getMid();
        String dateRange;
        if (dRange.isPresent()) {
            dateRange = dRange.get();
        } else {
            dateRange = "monthly";
        }

        int invoiceListSize;
        invoiceListSize = dashboardService.getMerchantInvoiceCount(mid,dateRange);
        model.addAttribute("invoiceListSize",invoiceListSize);
        double totalAmount;
        totalAmount = dashboardService.getMerchantTotalAmount(mid,dateRange);
        model.addAttribute("totalAmount",totalAmount);
        List<Double> totalAmountList = dashboardService.getMerchantRevenue(mid,dateRange);
        totalAmountList.clear();
        totalAmountList.add(1.0);
        totalAmountList.add(2.0);
        totalAmountList.add(3.0);
        totalAmountList.add(4.0);
        totalAmountList.add(5.0);
        totalAmountList.add(6.0);
        totalAmountList.add(7.0);
        totalAmountList.add(13.0);
        totalAmountList.add(8.0);
        totalAmountList.add(2.0);
        model.addAttribute("totalAmountList",totalAmountList);
        List<Integer> totalTransactionList = dashboardService.getMerchantTotalTransactions(mid,dateRange);
        totalTransactionList.clear();
        totalTransactionList.add(2);
        totalTransactionList.add(3);
        totalTransactionList.add(4);
        totalTransactionList.add(5);
        totalTransactionList.add(7);
        totalTransactionList.add(13);
        totalTransactionList.add(5);
        totalTransactionList.add(8);
        totalTransactionList.add(17);
        model.addAttribute("totalTransactionList",totalTransactionList);

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
        merchantServiceNew.addNewMerchant(merchant);
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

    public Merchant findLoggedInMerchant() {

        String username = null;
        Merchant merchant= null;

        username =securityService.getLoggedInUser();
        if(username !=null) {
            merchant= merchantReadRepository.findByEmail(username);
        }
        return merchant;
    }






}
