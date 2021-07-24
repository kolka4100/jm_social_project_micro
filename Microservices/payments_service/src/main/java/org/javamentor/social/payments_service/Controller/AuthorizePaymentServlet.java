package org.javamentor.social.payments_service.Controller;

import com.paypal.base.rest.PayPalRESTException;
import org.javamentor.social.payments_service.model.OrderDetail;
import org.javamentor.social.payments_service.service.PaymentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthorizePaymentServlet", value = "/payment-service")
public class AuthorizePaymentServlet extends HttpServlet {

    public AuthorizePaymentServlet() {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"payment-service\" method=\"post\">\n" +
                "    <button type=\"submit\">submit</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDetail testOrder = new OrderDetail("testProduct","100.0", "20.0", "10.0", "130.0");
        PaymentService paymentService = new PaymentService();

        try {
            String approvalLink = paymentService.authorizePayment(testOrder);
            response.sendRedirect(approvalLink);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }


    }
}
