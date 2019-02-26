package com.billt.core.notificationservice.Services;
import com.billt.core.datasourcebase.Service.ICustomerTokenService;
import com.billt.core.datasourcebase.entities.jpa.CustomerToken;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import com.billt.core.notificationservice.Models.NotificationRequestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;



@Service
public class NotificationPush {

    @Autowired
    @Qualifier(value="customerTokenService")
    ICustomerTokenService iCustomerTokenService;

    private static final Logger LOG = LoggerFactory.getLogger(NotificationPush.class);

    @Async
    public void pushNewInvoice(TransactionFlowRequestBean transactionFlowRequestBean) throws IOException{

        LOG.info("Notification Push for transaction : {}", transactionFlowRequestBean.getOrderId());
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                "https://fcm.googleapis.com/fcm/send");

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        //NotificationData notificationData = new NotificationData();

        //notificationData.setDetail("this is firebase push notification from java client (server)");
        //notificationData.setTitle("Hello Firebase Push Notification");
        //notificationRequestModel.setData(notificationData);
        notificationRequestModel.setData(transactionFlowRequestBean);
        //Client Token goes here - change to read from DB
        String ctid = transactionFlowRequestBean.getCid();
        CustomerToken customerToken = iCustomerTokenService.fetchCustomerToken(ctid);
        String token = customerToken.getToken();
        //String token = "dLTmYXux1PA:APA91bHyv_iU4sR89M-Vrh2RzsuQwZMb47su5O0EZTW6mK4MC3StBS_fiNUwDHaoo_nN0LpIPib2BIw2_13Hqo5EO9_Jys1jSm83LZ_oO8cHmD1QWawB5_lVAQGrHD7CRZdcOO1RQoUs"
        notificationRequestModel.setTo(token);


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");

        // server key of your firebase project goes here in header field.
        // You can get it from firebase console.
        //Server Key
        postRequest.addHeader("Authorization", "key=AAAAA2j2S4g:APA91bEw0s0bbdwAFrFWk8V3O1839dCM5rlPyPLQQlqfpLAnOCZcasMwQJ9w1drfM_nRy24YzTd_iXOtldhkZ3iyC6L47-9t4ymwazqHvtDtw9UCuRqLEpcRTCxiabM2cUBCC8QRRk4q");
        postRequest.setEntity(input);

        System.out.println("request:" + json);

        HttpResponse response = httpClient.execute(postRequest);
        //Change
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        } else if (response.getStatusLine().getStatusCode() == 200) {

            System.out.println("response:" + EntityUtils.toString(response.getEntity()));

        }
    }
}
