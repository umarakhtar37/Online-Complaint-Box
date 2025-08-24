package com.example.complaintproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.complaintproject.Entity.Victim;
import com.example.complaintproject.Repository.VictimRepo;


@RequestMapping("/")
@Controller
public class WebsiteController 
{

    @Autowired
    VictimRepo victimRepo;


    @RequestMapping({"/home","/"})
    public String home()
    {
        return "website/index.html";
    }

    @RequestMapping("/about")
    public String about()
    {
        return "website/about.html";
    }

    @RequestMapping("/contact")
    public String contact()
    {
        return "website/contact.html";
    }

    @RequestMapping("/createAccount")
    public String createAccount()
    {
        return "victim/createAccount.html";
    }



    @ResponseBody
    @PostMapping("/createAccount")
    public String saveVictim(Victim victim)
    {
        victim.setPassword(new BCryptPasswordEncoder().encode(victim.getPassword())); 
        // victim.setContact(new BCryptPasswordEncoder().encode(victim.getContact()));
        // victim.setEmail(new BCryptPasswordEncoder().encode(victim.getEmail()));
        victimRepo.save(victim);
        return "Account Created!";        
    }    
}
