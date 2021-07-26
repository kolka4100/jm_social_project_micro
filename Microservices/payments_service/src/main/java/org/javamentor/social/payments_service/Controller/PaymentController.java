package org.javamentor.social.payments_service.Controller;


import com.paypal.base.rest.PayPalRESTException;
import org.javamentor.social.payments_service.service.PaymentService;
import org.javamentor.social.payments_service.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("api/payment_service")
public class PaymentController {

    @Value("${spring.payment.service.errorUrl}")
    private String errorUrl;

    PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/get")
    private void testGetMethod(HttpServletResponse response) throws IOException {


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"pay\" method=\"post\">\n" +
                "    <button type=\"submit\">submit</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    @PostMapping("/pay")
    private String payTestProductMethod(){
        OrderDetail testOrder = new OrderDetail("testProduct", "100.0", "20.0", "10.0", "130.0");

        try {
            String approvalLink = paymentService.authorizePayment(testOrder);
            return "redirect:" + approvalLink;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:" + errorUrl;

    }
}
