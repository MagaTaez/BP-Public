package cz.vse.java.ploa00.bpbackend.delegate;

import cz.vse.java.ploa00.bpbackend.config.keycloak.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

public abstract class AbstractDelegate {

    @Autowired
    private NativeWebRequest nativeWebRequest;

    @Autowired
    private TokenValidationService tokenValidationService;

    private String getAccessToken() {
        String authorization = nativeWebRequest.getHeader("Authorization");

        String[] parts = authorization.split(" ");

        return parts[1];
    }

    private void checkRole(String role) {
       String accessToken =  getAccessToken();

       Map<String, Object> tokenDetails = tokenValidationService.getTokenDetails(accessToken);

       if ((boolean)tokenDetails.get("active")) {

           Map<String,Object> resourceAccess = (Map<String, Object>)tokenDetails.get("resource_access");

           Map<String,Object> client = (Map<String, Object>)resourceAccess.get("BP-client");

           List<String> roles = (List<String>) client.get("roles");

           if(!roles.contains(role)) {
               throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No role " + role);
           }
       } else {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not active token");
       }
    }

    public void checkManagerRole() {
        checkRole("manager");
    }

    public void checkEmployeeRole() {
        checkRole("employee");
    }
}
