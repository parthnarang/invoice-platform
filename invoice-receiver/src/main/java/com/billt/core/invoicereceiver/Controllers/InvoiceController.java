package com.billt.core.invoicereceiver.Controllers;


import com.billt.core.invoicereceiver.Model.InvoiceRequestBean;
import com.billt.core.invoicereceiver.enums.invoiceReceiver.ValidationResults;
import com.billt.core.invoicereceiver.Service.IInvoiceService;
import com.billt.core.invoicereceiver.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/invoicereceiver/")
public class InvoiceController {

    protected static Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    @Qualifier(value = "invoiceService")
    IInvoiceService invoiceService;

    @PostMapping("processInvoice")
    public void processInvoice(final HttpServletRequest request, final HttpServletResponse response,
                               final Model model, @RequestParam("MID") String merchantId) throws IOException, ServletException {
        final long startTime = System.currentTimeMillis();
        try {

            LOG.debug("Invoice Request Received for merchantId : {}", merchantId);
            InvoiceRequestBean invoiceRequestBean = new InvoiceRequestBean(request);
            processRequest(request, response, invoiceRequestBean, model);
            return;
        } catch (final Exception e) {
            LOG.error("SYSTEM_ERROR : ", e);
        } finally {
            LOG.info("Total time taken for ProcessTransactionController is {} ms", System.currentTimeMillis()
                    - startTime);
            return;
        }
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response,
                                InvoiceRequestBean invoiceRequestData, final Model model) throws IOException, ServletException {

        Response customResponse = processInvoiceRequest(invoiceRequestData);
        Assert.notNull(customResponse, "Service page response received was null");

        //parse response here
        //response.getOutputStream().wr(customResponse);
        //response.setContentType("text/html");

    }

    public Response processInvoiceRequest(final InvoiceRequestBean invoiceRequestData) {
        LOG.info("InvoiceRequestbean received : {}", invoiceRequestData);
        Response response=null;
        ValidationResults validationResults;
        validationResults = invoiceService.validatePaymentRequest(invoiceRequestData);

        switch (validationResults) {
            case CHECKSUM_VALIDATION_FAILURE:
                LOG.error("CHECKSUM_FAILED_ERROR_MSG", invoiceRequestData.getMid());

                break;
            case INVALID_DATA:
                LOG.error("INVALID DATA", invoiceRequestData.getMid());

                break;
            case VALIDATION_SUCCESS:
                response = invoiceService.processInvoiceRequest(invoiceRequestData);

        }
        return response;
    }




    }