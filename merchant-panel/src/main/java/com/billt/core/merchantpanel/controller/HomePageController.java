package com.billt.core.merchantpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel/")
public class HomePageController {

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("con", "dashboard");
        return "layout";
    }
    @GetMapping("invoice")
    public String invoice(Model model) {
        model.addAttribute("con", "invoice");
        return "layout";
    }
    @GetMapping("menu")
    public String menu(Model model) {
        model.addAttribute("con", "menu");
        return "layout";
    }


}
