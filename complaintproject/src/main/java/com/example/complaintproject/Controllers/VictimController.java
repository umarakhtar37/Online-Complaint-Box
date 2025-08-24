package com.example.complaintproject.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.complaintproject.Entity.Complaint;
import com.example.complaintproject.Entity.Conversation;
import com.example.complaintproject.Entity.Victim;
import com.example.complaintproject.Repository.ComplaintRepo;
import com.example.complaintproject.Repository.VictimRepo;

@RequestMapping("/victim")
@Controller
public class VictimController  
{

    @Autowired
    VictimRepo victimRepo;

    @Autowired
    ComplaintRepo complaintRepo;


    private Victim getLoggedInUser()
    {
        Object object=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=(UserDetails)object;
        Victim victim=(Victim)userDetails;
        return victim;
    }

    @RequestMapping("/home")                
    public String home(Model model)
    {
        Victim victim=getLoggedInUser();
        model.addAttribute("victim", victim);
        return "victim/home.html";
    }

    @RequestMapping("/addcomplaint")
    public String addComplaint()
    {
        return "victim/addComplaint.html";
    }

    @ResponseBody
    @PostMapping("/addcomplaint")
    public String saveComplaint(Complaint complaint,
                            @RequestParam("image") MultipartFile image)
    {
        try
        {
        complaint.setEvidance(image.getBytes());
        Victim victim=getLoggedInUser();
        complaint.setStatus(true);
        complaint.setVictim(victim);

        DateTimeFormatter dtf = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime date= LocalDateTime.now();
        complaint.setDate(dtf.format(date));
        List<Complaint> data=victim.getComplaint();
        data.add(complaint);
        victim.setComplaint(data);
        victimRepo.save(victim);
        return "complaint added";
        }
        catch(Exception e)
        {
            return e.getMessage();
        }   
    }

    
    
    @RequestMapping("/addmessage/{cid}")
    public String addMessage(@PathVariable("cid") int cid,Model model)
    {
        Complaint complaint=complaintRepo.findById(cid).get();
        List<Conversation> data=complaint.getConversation();
        model.addAttribute("complaint", complaint);
        model.addAttribute("data", data);
        return "/victim/addmessage";
    }

    @PostMapping("/addmessage")
    public String saveMessage(Conversation conversation,@RequestParam("cid") int cid)
    {
        
        StringBuffer sb = new StringBuffer(conversation.getMessage());
        sb.insert(0, "Victim: ");
        conversation.setMessage(sb.toString());

        Complaint complaint=complaintRepo.findById(cid).get();
        List<Conversation> data=complaint.getConversation();
        data.add(conversation);
        
        conversation.setComplaint(complaint);
        complaintRepo.save(complaint);
        return "redirect:/victim/addmessage/"+cid;
    }
    @RequestMapping("/displaycomplaint")
    public String displaycomplaint(Model model)
    {
        Victim victim=getLoggedInUser();
        List<Complaint> data=victim.getComplaint();
        model.addAttribute("victim",victim);
        model.addAttribute("data", data);
        for(Complaint item:data)
        {
            System.out.println(item.toString());
        }
        return "victim/displaycomplaint.html";
    }
    @RequestMapping("/displayconversation/{cid}")
    public String displayconversation(@PathVariable("cid") int cid,Model model)
    {
        Complaint complaint=complaintRepo.findById(cid).get();
        List<Conversation> data=complaint.getConversation();
        model.addAttribute("data", data);
        model.addAttribute("complaint", complaint);
        return "victim/displayconversation.html";
    }

    @ResponseBody
    @RequestMapping("/evidance/{cid}")
    public byte[] displayEvidance(@PathVariable("cid") int cid)
    {
        Complaint complaint=complaintRepo.findById(cid).get();
        return complaint.getEvidance();
    }
    
    @RequestMapping("/showEvidance/{cid}")
    public String showEvidance(@PathVariable("cid")int cid,Model model)
    {
        model.addAttribute("cid",cid);
        return "/victim/showEvidance.html";
    }
}
