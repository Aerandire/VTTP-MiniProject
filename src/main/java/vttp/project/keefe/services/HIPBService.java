package vttp.project.keefe.services;


//import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;

import vttp.project.keefe.model.Pwned;

@Service
public class HIPBService {

    public static final String HIBPU_SEARCH = "https://haveibeenpwned.com/api/v3/breachedaccount/";
    public static final String HIBPPW_SEARCH = "https://api.pwnedpasswords.com/range";
    public static final String BREACH = "/breachedaccount";

    // GIPHY_API_KEY
    @Value("${hibp.api.key}")
    private String hipbKey;

    RestTemplate template = new RestTemplate();

    public Pwned getResult (String email){

        String url = UriComponentsBuilder.fromUriString(HIBPU_SEARCH)
            .path(email)
            .queryParam("truncateResponse", "false")
            .queryParam("includeUnverified", "false")
            .toUriString();
            //System.out.printf(">>>>>> URL: %s\n", url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("hibp-api-key", hipbKey);
        headers.set(HttpHeaders.USER_AGENT, "PWStorageZone");  
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> resp = template.exchange(url,HttpMethod.GET,requestEntity,String.class);
        //System.out.printf(">>>>>> RESPONSE: %s\n", resp.getBody());
        return Pwned.create(resp.getBody());

    }

    public boolean pwnedOrNot(String email, String password){
        
        Boolean userEmail;
        //Boolean userPw;

        String url = UriComponentsBuilder.fromUriString(HIBPU_SEARCH)
            .path(email)
            .queryParam("truncateResponse", "false")
            .queryParam("includeUnverified", "false")
            .toUriString();
            //System.out.printf(">>>>>> URL2: %s\n", url);
        HttpHeaders headers = new HttpHeaders();
        headers.set("hibp-api-key", hipbKey);
        headers.set(HttpHeaders.USER_AGENT, "PWStorageZone");  
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<String> resp = template.exchange(url,HttpMethod.GET,requestEntity,String.class);
            //System.out.printf(">>>>>> RESPONSE2: %s\n", resp.getBody());
            if(resp.getBody().isEmpty()){
                userEmail = false;
            }
            userEmail = true;
        }catch(Exception ex){
            userEmail = false;
        } 

        /*String sha1pw = null;

        try{
            sha1pw = sha1(password);
        } catch (UnsupportedEncodingException e){
            System.err.printf("Error while processing pwd : %s%n", password);
        }

        String head = sha1pw.substring(0, 5);
        String tail = sha1pw.substring(5);

        String url2 = UriComponentsBuilder.fromUriString(HIBPPW_SEARCH)
            .path(head)
            .toUriString();
        
        try{
            ResponseEntity<String> resp2 = template.exchange(url2,HttpMethod.GET,null,String.class);
            System.out.printf(">>>>>> RESPONSE PW: %s\n", resp2.getBody());
            userPw = true;
        } catch (Exception e){
            userPw = false;
        }*/

        if(userEmail == true)
            return true;
        else
            return false;
        
    }

    /*public static String sha1(String pw) throws UnsupportedEncodingException {
        MessageDigest msgDigest = null;

        try{
            msgDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
        }

        String sha1pwd = bytesToHex(msgDigest.digest(pw.getBytes("utf-8")));
        return sha1pwd; 
    }

    public static String bytesToHex(byte[] bytes){

        StringBuffer hexStringBuffer = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            char[] hexDigits = new char[2];
            hexDigits[0] = Character.forDigit((bytes[i] >> 4) & 0xF, 16);
            hexDigits[1] = Character.forDigit((bytes[i] & 0xF), 16);
            String byteToHex = new String(hexDigits);

            hexStringBuffer.append(byteToHex);
        }

        return hexStringBuffer.toString().toUpperCase();
    }*/
    
}
