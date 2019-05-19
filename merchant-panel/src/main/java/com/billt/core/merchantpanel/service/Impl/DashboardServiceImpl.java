package com.billt.core.merchantpanel.service.Impl;

import com.billt.core.datasourcebase.collection.Invoice;
import com.billt.core.datasourcebase.repositories.mongo.read.InvoiceReadRepository;
import com.billt.core.merchantpanel.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("dashBoardService")
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    InvoiceReadRepository invoiceReadRepository;

    @Override
    public int getMerchantInvoiceCount(String mid, String dRange){
        List<Invoice>invoiceList = getMerchantInvoices(mid,dRange);
        return invoiceList.size();
    }

    @Override
    public List<Invoice> getMerchantInvoices(String mid, String dRange) {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date from,to;
        if(dRange.compareTo("daily")==0) {
            from = new Date(System.currentTimeMillis() - (DAY_IN_MS));
            to = new Date();
        }else if(dRange.compareTo("weekly")==0){
            from = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
            to = new Date();

        }else if(dRange.compareTo("monthly")==0){
            from = new Date(System.currentTimeMillis() - (30 * DAY_IN_MS));
            to = new Date();
        }else{
            from = new Date(System.currentTimeMillis() - (365 * DAY_IN_MS));
            to = new Date();
        }
        return invoiceReadRepository.findByDateBetweenAndMid(from,to,mid);
    }

    @Override
    public double getMerchantTotalAmount(String mid, String id) {
        List<Invoice> invoiceList = getMerchantInvoices(mid,id);
        double totalAmt = 0;
        for(int i=0; i<invoiceList.size();++i){
            totalAmt += Double.valueOf(invoiceList.get(i).getTotalAmt());
        }

        return totalAmt;
    }
}
