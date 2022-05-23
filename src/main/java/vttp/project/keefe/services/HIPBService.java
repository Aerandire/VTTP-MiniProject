package vttp.project.keefe.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp.project.keefe.model.Pwned;

@Service
public class HIPBService {

    public static final String HIBPU_SEARCH = "https://haveibeenpwned.com/api/v3/breachedaccount/";
    public static final String HIBPPW_SEARCH = "https://api.pwnedpasswords.com/range";
    public static final String BREACH = "/breachedaccount";

    // GIPHY_API_KEY
    @Value("${hibp.api.key}")
    private String hipbKey;

    public Pwned getResult (String email){

        RestTemplate template = new RestTemplate();

        String url = UriComponentsBuilder.fromUriString(HIBPU_SEARCH)
            .path(email)
            .toUriString();
            System.out.printf(">>>>>> URL: %s\n", url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("hibp-api-key", hipbKey);
        headers.set(HttpHeaders.USER_AGENT, "PWStorageZone");  
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = template.exchange(url,HttpMethod.GET,requestEntity,String.class);
        System.out.printf(">>>>>> RESPONSE: %s\n", resp.getBody());
        return Pwned.create(resp.getBody());
    }
    
}
