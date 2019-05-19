package com.billt.core.merchantpanel.controller;

import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.merchantpanel.service.MerchantServiceNew;
import com.billt.core.merchantpanel.service.Impl.SecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel/")
public class HomePageController {

    private static final Logger log = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    private MerchantServiceNew merchantServiceNew;

    @Autowired
    private SecurityServiceImpl securityService;

/*    @GetMapping("home")
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
    }*/
/*
    @PostMapping("registration")
    public String addNewMerchant(@ModelAttribute Merchant merchant) {
        log.info("Receiving request to add new merchant");
        merchantServiceNew.addNewMerchant(merchant);
        log.info("Creation for request is done");
        securityService.autoLogin(merchant.getEmail(), merchant.getPasswordConfirm());
        return "success";
    }*/


/*
    @GetMapping("invoice")
    public String invoice(Model model) {
        model.addAttribute("con", "invoice");
        return "layout";
    }
*/


}
