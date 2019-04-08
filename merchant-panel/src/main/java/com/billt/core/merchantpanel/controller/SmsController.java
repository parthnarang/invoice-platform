package com.billt.core.merchantpanel.controller;

import com.billt.core.datasourcebase.Service.Impl.IUrlMapperImpl;
import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.entities.jpa.InvoiceUrl;
import com.billt.core.datasourcebase.repositories.mongo.read.InvoiceReadRepository;
import com.billt.core.merchantpanel.service.SmsInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.billt.core.merchantpanel.Constants.MerchantPanelConstants.BILLT_URL;


@RequestMapping("/t/")
@Controller
public class SmsController {

    @Autowired
    SmsInvoiceService smsInvoiceService;

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @GetMapping("/{id}")
    public String getInvoice(@PathVariable String id) {
        smsInvoiceService.constructSmsInvoice(id);
        return "";
    }
}
