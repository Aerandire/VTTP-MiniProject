package vttp.project.keefe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vttp.project.keefe.model.Accounts;
import vttp.project.keefe.model.CustomUserDetails;
import vttp.project.keefe.model.User;
import vttp.project.keefe.services.AccountsService;
import vttp.project.keefe.services.HIPBService;
import vttp.project.keefe.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@Controller
@RequestMapping(path="/dashboard")
public class DashBoardController {
    
    @Autowired
    private UserService uSvc;

    @Autowired
    private HIPBService hSvc;

    @Autowired
    private AccountsService acctSvc;

    @GetMapping
    public String viewDashboard(
        @AuthenticationPrincipal CustomUserDetails uDetails, 
        Model m){
        
        String userEmail = uDetails.getUsername();
        User uu = uSvc.loadUserByEmail(userEmail);
        m.addAttribute("user", uu);
        m.addAttribute("account", new Accounts());
        List<Accounts> accts = acctSvc.getAccounts(uu.getId());
        if(!accts.isEmpty())
            m.addAttribute("accts", accts);

        return "dashboard";
    }

    @PostMapping
    public String addAccountIn(Accounts acct){
       
        boolean saved = acctSvc.addAccount(acct);
        if(saved != true)
            return "error_saving";
        else
            return "redirect:/dashboard";

    }

    @PostMapping(path="/delete")
    public String deleteAcct(Accounts acct){

        boolean done = acctSvc.delAcct(acct);
        if(done != true)
            return "error_del";
        else
            return "redirect:/dashboard";

    }

    @PostMapping(path="/checkUP")
    public String checkAcc(Accounts acct, RedirectAttributes rediAttr){
        
        String email = acct.getEmail();
        String password = acct.getPassword();
        boolean pwned = hSvc.pwnedOrNot(email, password);

        if(pwned == false)
            rediAttr.addFlashAttribute("success", "This account is safe");
        else if(pwned == true)
            rediAttr.addFlashAttribute("error", "This account has been pwned!!!");
        return "redirect:/dashboard";
    }
    

}
