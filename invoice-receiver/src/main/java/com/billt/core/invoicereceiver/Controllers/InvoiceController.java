package com.billt.core.invoicereceiver.Controllers;


import com.billt.core.datasourcebase.model.ItemListWrapper;
import com.billt.core.datasourcebase.model.invoiceReceiver.InvoiceItem;
import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.service.IInvoiceService;
import com.billt.core.invoicereceiver.enums.ResponseCode;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.billt.core.invoicereceiver.Constants.InvoiceReceiverConstants.*;

@Controller
@RequestMapping("/invoicereceiver/")
public class InvoiceController {

    protected static Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    @Qualifier(value = "invoiceService")
    IInvoiceService invoiceService;

    @PostMapping(value = "processInvoice",consumes = "application/json")
    public void processInvoice(final HttpServletRequest request, final HttpServletResponse response,
                               final Model model) throws IOException, ServletException {

        final long startTime = System.currentTimeMillis();
        try {

            LOG.debug("Invoice Request Received");
            JSONObject jsonRequest = mapToJson(request);
            LOG.info("request received = {}",jsonRequest);
            InvoiceRequestBean invoiceRequestBean = mapToInvoiceRequestBean(jsonRequest);
            processRequest(request, response, invoiceRequestBean, model);


        } catch (final Exception e) {
            response.getWriter().print(new JSONObject(ResponseCode.INTERNAL_SERVER_ERROR));
            response.setContentType("application/json");

            LOG.error("SYSTEM_ERROR : ", e);
        } finally {
            LOG.info("Total time taken for ProcessTransactionController is {} ms", System.currentTimeMillis()
                    - startTime);
            return;
        }
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response,
                                InvoiceRequestBean invoiceRequestData, final Model model) throws IOException, ServletException {

        ResponseCode customResponse = processInvoiceRequest(invoiceRequestData);
/*        Assert.notNull(customResponse, "service page response received was null");*/


        response.getWriter().print(new JSONObject(customResponse));
        response.setContentType("application/json");

    }

    public ResponseCode processInvoiceRequest(final InvoiceRequestBean invoiceRequestData) throws IOException {
        LOG.info("InvoiceRequestbean received : {}", invoiceRequestData);
        ResponseCode responseCode=null;
        ValidationResults validationResults;
        validationResults = invoiceService.validatePaymentRequest(invoiceRequestData);

        switch (validationResults) {
            case CHECKSUM_VALIDATION_FAILURE:
                LOG.error("CHECKSUM_FAILED_ERROR_MSG", invoiceRequestData.getMid());
                responseCode = ResponseCode.CHECKSUM_MISMATCH;
                break;
            case INVALID_DATA:
                LOG.error("INVALID DATA", invoiceRequestData.getMid());
                responseCode = ResponseCode.INVALID_REQUEST;

                break;
            case VALIDATION_SUCCESS:
                responseCode = invoiceService.processInvoiceRequest(invoiceRequestData);

        }
        return responseCode;
    }

    private JSONObject mapToJson(HttpServletRequest request) throws JSONException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

            JSONObject jsonObject =  new JSONObject(jb.toString());
        return jsonObject;

    }

    private InvoiceRequestBean mapToInvoiceRequestBean(JSONObject jsonObject) throws JSONException{

        InvoiceRequestBean invoiceRequestBean = new InvoiceRequestBean();

            if(jsonObject.has(MID))
                invoiceRequestBean.setMid(jsonObject.getString(MID));
            if(jsonObject.has(VID))
                invoiceRequestBean.setVid(jsonObject.getString(VID));
            if(jsonObject.has(ORDER_ID))
                invoiceRequestBean.setOrderId(jsonObject.getString(ORDER_ID));
            if(jsonObject.has(CHECKSUMHASH))
                invoiceRequestBean.setChecksumhash(jsonObject.getString(CHECKSUMHASH));
            if(jsonObject.has(MOBILE_NO))
                invoiceRequestBean.setMobileNo(jsonObject.getString(MOBILE_NO));
            if(jsonObject.has(EMAIL))
                invoiceRequestBean.setEmail(jsonObject.getString(EMAIL));
            if(jsonObject.has(ITEM_LIST)) {
                JSONObject itemListWrapper = jsonObject.getJSONObject(ITEM_LIST);

                ItemListWrapper itemListWrapperNew= new ItemListWrapper();
                List<InvoiceItem> invoiceItemList = new ArrayList<>();
                for (int i = 0; i < itemListWrapper.getJSONArray("invoiceItems").length(); i++) {
                    JSONObject itemObject = itemListWrapper.getJSONArray("invoiceItems").getJSONObject(i);
                    InvoiceItem invoiceItem = new InvoiceItem();
                    invoiceItem.setAMOUNT(itemObject.getString("amount"));
                    invoiceItem.setDESCRIPTION(itemObject.getString("description"));
                    invoiceItem.setQUANTITY(itemObject.getString("quantity"));
                    invoiceItem.setRATE(itemObject.getString("rate"));

                    invoiceItemList.add(invoiceItem);

                }
                itemListWrapperNew.setInvoiceItems(invoiceItemList);
                invoiceRequestBean.setItemListWrapper(itemListWrapperNew);
            }
        if(jsonObject.has(MERCHANT_NAME))
            invoiceRequestBean.setMerchantName(jsonObject.getString(MERCHANT_NAME));
        if(jsonObject.has(CUST_ID))
            invoiceRequestBean.setBilltId(jsonObject.getString(CUST_ID));
        if(jsonObject.has(ADDRESS))
            invoiceRequestBean.setAddress(jsonObject.getString(ADDRESS));
        if(jsonObject.has(PHONE_NO_LIST))
            invoiceRequestBean.setPhoneNoList(jsonObject.getString(PHONE_NO_LIST));
        if(jsonObject.has(TIME))
            invoiceRequestBean.setTime(jsonObject.getString(TIME));
        if(jsonObject.has(DATE))
            invoiceRequestBean.setDate(jsonObject.getString(DATE));
        if(jsonObject.has(GST))
            invoiceRequestBean.setGst(jsonObject.getString(GST));
        if(jsonObject.has(TOTAL_AMT))
            invoiceRequestBean.setTotalAmt(Double.parseDouble(jsonObject.getString(TOTAL_AMT)));
        if(jsonObject.has(VAT))
            invoiceRequestBean.setVat(Double.parseDouble(jsonObject.getString(VAT)));
        if(jsonObject.has(NET))
            invoiceRequestBean.setNet(Double.parseDouble(jsonObject.getString(NET)));
        if(jsonObject.has(CUSTOMER_EMAIL_TO))
            invoiceRequestBean.setCustomerEmail(jsonObject.getString(CUSTOMER_EMAIL_TO));
        if(jsonObject.has(CUSTOMER_MOBILE_NO))
            invoiceRequestBean.setCustomerMobileNO(jsonObject.getString(CUSTOMER_MOBILE_NO));

                return invoiceRequestBean;
    }




    }