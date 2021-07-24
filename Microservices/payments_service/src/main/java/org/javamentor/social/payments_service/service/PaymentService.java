package org.javamentor.social.payments_service.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.javamentor.social.payments_service.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private static final String CLIENT_ID = "AYX9mJH3Dogx-S4wvqC8IN-wh-jCNcAxCf9cuqrNaBfg4J44aa8ijMxGk0nDIRvsXOsDYZNuhUyPLqLN";
    private static final String CLIENT_SECRET = "EE0Hh-oPqf5szggjqqAliZCZ-HNaNRPzP0agG2P1yguMibl43yG8Xh5EVsJMbwl6LfaczOtQBAQlE0oG";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction)
                .setRedirectUrls(redirectUrls)
                .setPayer(payer)
                .setIntent("authorize");


        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println(approvedPayment);
        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }
        return approvalLink;
    }


    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();

        //Here must be URL of Cancel to pay
        redirectUrls.setCancelUrl("http://localhost:8061/pay/cancel");
        //Here must be URL of review to pay
        redirectUrls.setReturnUrl("http://localhost:8061/pay/return");

        return redirectUrls;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Александр")
                .setLastName("Достоевский")
                .setEmail("sb-lxwlr6879427@personal.example.com");
        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubtotal());
        details.setTax(orderDetail.getTax());

        Amount amount = new Amount();
        amount.setCurrency("RUB");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getProductName());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("RUB")
                .setName(orderDetail.getProductName())
                .setPrice(orderDetail.getSubtotal())
                .setTax(orderDetail.getTax())
                .setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }
}

