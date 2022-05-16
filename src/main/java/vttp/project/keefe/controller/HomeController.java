package vttp.project.keefe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.project.keefe.model.CustomUserDetails;
import vttp.project.keefe.model.User;
import vttp.project.keefe.repositories.UserRepository;

@Controller
@RequestMapping(path="/")
public class HomeController {
    
    @Autowired
    private UserRepository uRepo;

    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String RegisterPage(Model m){
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process_register")
    public String processREgister(User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        uRepo.save(user);

        return "registration_success";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(
        @AuthenticationPrincipal CustomUserDetails uDetails, 
        Model m){
        
        String userEmail = uDetails.getUsername();
        User uu = uRepo.findByEmail(userEmail);

        m.addAttribute("user", uu);

        return "dashboard";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
         
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/";
    }
}
