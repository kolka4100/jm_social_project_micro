package jmsocialproject.springgateway.validator;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Log
public class RouteValidationImpl implements RouteValidation {

    private final List<String> openApi;

    private final Map<String, List<String>> mapClosedApi;

    private final String jwtSecret;

    public RouteValidationImpl( @Value("${jwt.secret}") final String jwtSecret) {
        this.openApi = new ArrayList<>();
        openApi.add("/api/rest/auth/login");
        openApi.add("/api/rest/auth/registration");


        List<String> closedUserApi = new ArrayList<>();
        List<String> closedModeratorApi = new ArrayList<>();
        List<String> closedAdminApi = new ArrayList<>();

        closedUserApi.add("/api/user");
        closedModeratorApi.addAll(closedUserApi);
        closedModeratorApi.add("/api/moderator");
        closedAdminApi.addAll(closedModeratorApi);
        closedAdminApi.add("/api/admin");

        this.mapClosedApi = new HashMap<>();
        mapClosedApi.put("ROLE_USER", closedUserApi);
        mapClosedApi.put("ROLE_MODERATOR", closedModeratorApi);
        mapClosedApi.put("ROLE_ADMIN", closedAdminApi);

        this.jwtSecret = jwtSecret;
    }

    @Override
    public boolean isOpenApi(String url) {
        return openApi.contains(url);
    }





    @Override
    public boolean validateToken(String token, String url) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

            String roleName = claims.get("role", String.class);
            if(!mapClosedApi.get(roleName).contains(url)){
                return false;
            }



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
