package com.billt.core.notificationservice.Helpers;

import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SmsHelper {

    private static final Logger LOG = LoggerFactory.getLogger(EmailHelper.class);
    public String sendSms(TransactionFlowRequestBean transactionFlowRequestBean, String transactionUrl) {
        try {
            // Construct data
            String apiKey = "apikey=" + "CGA2YcG+ttQ-ELNPeJ4pHlQQ6TMLmb94Q5P0xnyxmn";
            String message = "&message=" + "This is a BillT message: " + transactionUrl;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "91" + transactionFlowRequestBean.getMobileNo();


            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;

            LOG.info("Email Sender emailMessage " + data);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                LOG.info("Textlocal response line: " + line);
                stringBuffer.append(line);
            }
            rd.close();
            LOG.info("Textlocal response: " + stringBuffer.toString());
            LOG.info("Textlocal response length: " + stringBuffer.length());
            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            return "Error "+e;
        }
    }
}

