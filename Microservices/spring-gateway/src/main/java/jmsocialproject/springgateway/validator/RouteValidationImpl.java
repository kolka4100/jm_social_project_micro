package jmsocialproject.springgateway.validator;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log
public class RouteValidationImpl implements RouteValidation {

    List<String> openApi;

    private String jwtSecret;

    public RouteValidationImpl(@Value("#{'${openApi.list}'.split(',')}") final List<String> openApi,     @Value("${jwt.secret}") final String jwtSecret) {
        this.openApi = openApi;
        this.jwtSecret = jwtSecret;
    }

    @Override
    public boolean isOpenApi(String url) {
        return openApi.contains(url);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.severe("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
        } catch (Exception e) {
            log.severe("invalid token");
        }
        return false;
    }
}
