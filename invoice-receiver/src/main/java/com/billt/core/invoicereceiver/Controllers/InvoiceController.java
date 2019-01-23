package com.billt.core.invoicereceiver.Controllers;


import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.Service.IInvoiceService;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

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
                               final Model model, @RequestParam("MID") String merchantId) throws IOException, ServletException {
        System.out.println("sddd");
        final long startTime = System.currentTimeMillis();
        try {

            LOG.debug("Invoice Request Received for merchantId : {}", merchantId);
            JSONObject jsonRequest = mapToJson(request);
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
        Assert.notNull(customResponse, "Service page response received was null");


        response.getWriter().print(new JSONObject(customResponse));
        response.setContentType("application/json");

    }

    public ResponseCode processInvoiceRequest(final InvoiceRequestBean invoiceRequestData) {
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
            if(jsonObject.has(DATA))
                invoiceRequestBean.setData(jsonObject.getJSONObject(DATA));
            if(jsonObject.has(ORDER_ID))
                invoiceRequestBean.setOrderId(jsonObject.getString(ORDER_ID));
            if(jsonObject.has(CHECKSUMHASH))
                invoiceRequestBean.setChecksumhash(jsonObject.getString(CHECKSUMHASH));
            if(jsonObject.has(MOBILE_NO))
                invoiceRequestBean.setMobileNo(jsonObject.getString(MOBILE_NO));
            if(jsonObject.has(EMAIL))
                invoiceRequestBean.setEmail(jsonObject.getString(EMAIL));

        return invoiceRequestBean;
    }





    }