package com.billt.core.datasourcebase.Service.Impl;

import com.billt.core.datasourcebase.Service.IUrlMapperService;
import com.billt.core.datasourcebase.entities.jpa.InvoiceUrl;
import com.billt.core.datasourcebase.repositories.jpa.read.UrlReadRepository;
import com.billt.core.datasourcebase.repositories.jpa.write.UrlWriteRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.billt.core.datasourcebase.Constants.DatabaseSourceConstants.BILLT_URL;

@Service("urlService")
public class IUrlMapperImpl implements IUrlMapperService {

    @Autowired
    UrlReadRepository urlReadRepository;

    @Autowired
    UrlWriteRepository urlWriteRepository;


    private String constructTransactionUrl(){
        String url = BILLT_URL;
        String generatedString = generatingRandomAlphanumericString();
        String result = url + generatedString;
        return result;
    }

    private String generatingRandomAlphanumericString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(6);
        return generatedString;
    }

    @Override
    public String mapUrl(String transactionId) {
        String curl = constructTransactionUrl();
        while(fetchTransaction(curl)!=null){
            curl = constructTransactionUrl();
        }
        InvoiceUrl url = new InvoiceUrl();
        url.setTransactionId(transactionId);
        url.setUrl(curl);
        url.setCreatedOn(new Date());
        url.setUpdatedOn(new Date());
        urlWriteRepository.save(url);
        return curl;
    }

    @Override
    public InvoiceUrl fetchTransaction(String curl) {
        InvoiceUrl url = null;
        if(curl != null){
            url = urlReadRepository.findTransactionByUrl(curl);
        }

        return url;

    }
}
