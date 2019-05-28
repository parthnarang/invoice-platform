package com.billt.core.merchantpanel.controller;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.merchantpanel.service.DashboardService;
import com.billt.core.merchantpanel.service.Impl.SecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dashboardpanel/")
public class DashboardController {
    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);


    @Autowired
    SecurityServiceImpl securityService;

    @Autowired
    private MerchantReadRepository merchantReadRepository;

    @Autowired
    DashboardService dashboardService;

    @GetMapping("dashboard/transactions/{id}")
    public String getTransactions(Model model, @PathVariable String id){
        Merchant merchant = findLoggedInMerchant();
        String mid = merchant.getMid();
        int invoiceListSize;
        invoiceListSize = dashboardService.getMerchantInvoiceCount(mid,id);
        model.addAttribute("invoiceListSize",invoiceListSize);
        return "transactionVolume";
    }

    @GetMapping("dashboard/totalAmount/{id}")
    public String getTotalAmount(Model model, @PathVariable String id){
        Merchant merchant = findLoggedInMerchant();
        String mid = merchant.getMid();
        double totalAmount;
        totalAmount = dashboardService.getMerchantTotalAmount(mid,id);
        model.addAttribute("totalAmount",totalAmount);
        return "merchantTotalAmount";
    }

    @GetMapping("dashboard/allMerchantTransactions/{id}")
    public String getAllMerchantTransactions(Model model, @PathVariable String id){
        Merchant merchant = findLoggedInMerchant();
        String mid = merchant.getMid();
        List<Double> totalAmountList = dashboardService.getMerchantRevenue(mid,id);
        model.addAttribute("totalAmountList",totalAmountList);
        return "merchantTotalAmountList";
    }

    @GetMapping("dashboard/allMerchantTransactionsCount/{id}")
    public String getAllMerchantTransactionCount(Model model, @PathVariable String id){
        Merchant merchant = findLoggedInMerchant();
        String mid = merchant.getMid();
        List<Integer> totalTransactionList = dashboardService.getMerchantTotalTransactions(mid,id);
        model.addAttribute("totalTransactionList",totalTransactionList);
        return "merchantTransactionCountList";
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