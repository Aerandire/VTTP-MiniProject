package vttp.project.keefe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;


@Controller
@RequestMapping(path="/dashboard")
public class DashBoardController {
    


    @PostMapping(path = "/addAccount", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addAccountIn(){
        return "checker";
    }

   

}
