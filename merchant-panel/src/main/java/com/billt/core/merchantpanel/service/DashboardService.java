package com.billt.core.merchantpanel.service;

import com.billt.core.datasourcebase.collection.Invoice;

import java.util.List;

public interface DashboardService {
    List<Invoice> getMerchantInvoices(String mid, String dRange);

    double getMerchantTotalAmount(String mid, String id);

    int getMerchantInvoiceCount(String mid, String id);

    List<Double> getMerchantRevenue(String mid, String id);

    List<Integer> getMerchantTotalTransactions(String mid, String id);
}
