package com.example.complaintproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.complaintproject.Entity.Admin;
import com.example.complaintproject.Entity.Victim;
import com.example.complaintproject.Repository.AdminRepo;
import com.example.complaintproject.Repository.VictimRepo;
@Service
public class VictimService implements UserDetailsService
{
    @Autowired
    VictimRepo victimRepo;

    @Autowired
    AdminRepo adminRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Victim victim=victimRepo.findVictimByAadhar(username);
        Admin admin=adminRepo.findAdminByUsername(username);
        if(victim!=null)
        {
            return victim;
        }
        if(admin!=null)
        {
            return admin;
        }   
            System.out.println("no victim or admin fetched!");
            return null;
        
    }
    
}
