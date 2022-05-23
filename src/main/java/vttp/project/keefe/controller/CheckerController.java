package vttp.project.keefe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.project.keefe.model.CustomUserDetails;
import vttp.project.keefe.model.Pwned;
import vttp.project.keefe.model.User;
import vttp.project.keefe.services.HIPBService;
import vttp.project.keefe.services.UserService;

@Controller
@RequestMapping(path="/checker")
public class CheckerController {

    @Autowired
    private UserService uSvc;

    @Autowired
    private HIPBService hSvc;
    
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String checkAccount(@AuthenticationPrincipal CustomUserDetails uDetails,
    @RequestBody MultiValueMap<String,String> form, 
    Model m){

        String userEmail = uDetails.getUsername();
        User uu = uSvc.loadUserByEmail(userEmail);
        m.addAttribute("user", uu);

        String email = form.getFirst("email");
        m.addAttribute("email", email);
        
        Pwned pwnDomains = hSvc.getResult(email);

        m.addAttribute("pwnDomains", pwnDomains);

        return "results";
    }
}