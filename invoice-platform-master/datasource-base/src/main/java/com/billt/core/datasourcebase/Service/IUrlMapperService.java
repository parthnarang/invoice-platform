package com.billt.core.datasourcebase.Service;

import com.billt.core.datasourcebase.entities.jpa.InvoiceUrl;

public interface IUrlMapperService {
    public String mapUrl(String transaction);
    public InvoiceUrl fetchTransaction(String url);
}
