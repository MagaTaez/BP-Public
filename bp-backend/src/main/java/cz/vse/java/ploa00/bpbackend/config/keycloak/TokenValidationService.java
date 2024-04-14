package cz.vse.java.ploa00.bpbackend.config.keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class TokenValidationService {

    @Autowired
    private Environment env;

    public Map<String, Object> getTokenDetails(String token) {

        RestTemplate restTemplate = new RestTemplate();

        String url = env.getProperty("keycloak.auth-server-url") + "/realms/" + env.getProperty("keycloak.realm") + "/protocol/openid-connect/token/introspect";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", token);
        map.add("client_id", env.getProperty("keycloak.client-id"));
        map.add("client_secret", env.getProperty("keycloak.client-secret"));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        return restTemplate.postForObject(url, entity, Map.class);
    }


}
