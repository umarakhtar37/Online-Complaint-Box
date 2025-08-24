package com.example.complaintproject.Controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.complaintproject.Entity.Complaint;
import com.example.complaintproject.Entity.Conversation;
import com.example.complaintproject.Entity.Victim;
import com.example.complaintproject.Repository.ComplaintRepo;
import com.example.complaintproject.Repository.VictimRepo;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
    @Autowired
    ComplaintRepo complaintRepo;

    @Autowired
    VictimRepo victimRepo;

    @RequestMapping("/seecomplaint")
    public String seecomplaint(Model model)
    {
        List<Complaint> data=complaintRepo.findByStatusTrue();
        model.addAttribute("data", data);
        model.addAttribute("title", "Pending Complaints");
        return "admin/seecomplaint.html";
    }

    @RequestMapping("/seeCloseComplaint")
    public String seeCloseComplaint(Model model)
    {
        List<Complaint> data=complaintRepo.findByStatusFalse();
        model.addAttribute("data", data);
        model.addAttribute("title", "Closed Complaints");
        return "admin/seecomplaint.html";
    }
    @RequestMapping("/closeComplaint/{cid}")
    public String closeComplaint(@PathVariable("cid") int cid)
    {
        Complaint complaint=complaintRepo.findById(cid).get();
        complaint.setStatus(false);
        complaintRepo.save(complaint);
        return "redirect:/admin/seecomplaint";
    }
    
    @RequestMapping("/seevictim/{vid}")
    public String seevictim(@PathVariable("vid") int vid,Model model)
    {
        Victim victim=victimRepo.findById(vid).get();
        model.addAttribute("victim", victim);
        return "admin/victimprofile.html";
    }

    @RequestMapping("/seevictim")
    public String seevictim(Model model)
    {
        List<Victim> data=victimRepo.findAll();
        model.addAttribute("data", data);
        return "admin/seevictim.html";
    }

    @RequestMapping("/seecomplaint/{vid}")
    public String seeVictimComplaint(@PathVariable("vid") int vid,Model model)
    {
        List<Complaint> data=victimRepo.findById(vid).get().getComplaint();
        model.addAttribute("data", data);
        return "admin/seeVictimComplaint.html";
    }

    @RequestMapping("/seeconversation/{cid}")
    public String getConversation(@PathVariable("cid") int cid,
                                            Model model)
    {
        List<Conversation> data=complaintRepo.findById(cid)
                            .get().getConversation();
                            
        model.addAttribute("data", data);
        return "admin/seeConversation.html";
    }   

    @RequestMapping("/addmessage/{cid}")
    public String addMessage(@PathVariable("cid") int cid,Model model)
    {
        Complaint complaint=complaintRepo.findById(cid).get();
        List<Conversation> data=complaint.getConversation();
        model.addAttribute("data", data);
        model.addAttribute("complaint",complaint);
        return "admin/addmessage.html";
    }

    @PostMapping("/addmessage/{cid}")
    public String saveMessage(@RequestParam("cid") int cid,
                                @RequestParam("message") String message)
    {
        StringBuffer sb = new StringBuffer(message);
        message=sb.insert(0,"Admin: ").toString();
        Conversation conversation = new Conversation();
        conversation.setMessage(message);

        Complaint complaint=complaintRepo.findById(cid).get();
        complaint.getConversation().add(conversation);
        complaintRepo.save(complaint);
        return "redirect:/admin/addmessage/"+cid;      
    }
   
    @ResponseBody
    @RequestMapping("/evidance/{cid}")
    public byte[] displayEvidance(@PathVariable("cid") int cid)
    {
        Complaint complaint=complaintRepo.findById(cid).get();
        return complaint.getEvidance();
    }

    @RequestMapping("/seeEvidance/{cid}")
    public String showEvidance(@PathVariable("cid") int cid,Model model)
    {
        model.addAttribute("cid", cid);
        return "/admin/showEvidance.html";
    }

}
