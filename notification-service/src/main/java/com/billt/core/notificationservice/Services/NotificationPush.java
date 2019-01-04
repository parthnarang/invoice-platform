package com.billt.core.notificationservice.Services;
import com.billt.core.datasourcebase.model.invoiceReceiver.TransactionFlowRequestBean;
import org.springframework.stereotype.Service;
import com.billt.core.notificationservice.Models.NotificationData;
import com.billt.core.notificationservice.Models.NotificationRequestModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.lang.reflect.Type;



@Service
public class NotificationPush {

    public void pushNewInvoice(TransactionFlowRequestBean transactionFlowRequestBean) throws IOException{
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
        //Change
        notificationRequestModel.setTo("eHp6V2Wtr4I:APA91bFLHRuScumQB0lnNRmeirxu5kV2lUPDZ8SbZKz");


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");

        // server key of your firebase project goes here in header field.
        // You can get it from firebase console.
        //Change
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
