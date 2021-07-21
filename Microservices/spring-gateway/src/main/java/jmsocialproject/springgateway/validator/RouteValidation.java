package jmsocialproject.springgateway.validator;

public interface RouteValidation {
    boolean isOpenApi(String url);

    boolean validateToken(String token);
}
