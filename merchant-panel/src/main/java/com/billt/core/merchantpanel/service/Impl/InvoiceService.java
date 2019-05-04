package com.billt.core.merchantpanel.service.Impl;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.repositories.jpa.read.MerchantReadRepository;
import com.billt.core.merchantpanel.Entities.MenuItemEntity;
import com.billt.core.merchantpanel.controller.MerchantController;
import com.billt.core.merchantpanel.model.InvoiceBean;
import com.billt.core.merchantpanel.repositories.read.MenuItemReadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private MerchantReadRepository merchantReadRepository;

    @Autowired
    MenuItemReadRepository menuItemReadRepository;

    @Autowired
    SecurityServiceImpl securityService;

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);



    private Merchant findLoggedInMerchant() {
        String username = null;
        Merchant merchant= null;

        username =securityService.getLoggedInUser();
        if(username !=null) {
            merchant= merchantReadRepository.findByEmail(username);
        }
        return merchant;
    }

    public List<MenuItemEntity> findMerchantMenuItems(){
        Merchant merchant = findLoggedInMerchant();

        List<MenuItemEntity> list =menuItemReadRepository.findAllBymerchant(merchant);
        return list;


    }

    public void getNewInvoice(Model model){

        List<MenuItemEntity> menuList = findMerchantMenuItems();

        Merchant merchant = merchantReadRepository.findById(findLoggedInMerchant().getId());

        InvoiceBean invoiceBean = new InvoiceBean();
        invoiceBean.setAddress(merchant.getMerchantAddress());
        invoiceBean.setMerchantName(merchant.getMerchantName());
        invoiceBean.setPhoneNoList(merchant.getContactList());
        invoiceBean.setTinNo(merchant.getTinNo());
        invoiceBean.setGst(merchant.getGstNo());
        model.addAttribute("invoice",invoiceBean);
        model.addAttribute("menuList",menuList);
        model.addAttribute("con", "invoice");
    }

    public void sendNewInvoice(InvoiceBean invoiceBean){

        log.info(""+invoiceBean);
        URI uri=null;

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080/invoicereceiver/processInvoice?MID=mer1001";
        try {
            uri = new URI(baseUrl);
        } catch (Exception exception){

        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "USA");

        HttpEntity<InvoiceBean> request = new HttpEntity<>(invoiceBean, headers);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
    }
}
