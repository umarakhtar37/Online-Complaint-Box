package com.example.complaintproject.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Victim implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int vid;    
    String name,aadhar,contact,password,email,address,state;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Complaint> complaint;


    public Victim() {
    }
    public Victim(int vid, String name, String aadhar, String contact, String password, String email, String address,
            String state, List<Complaint> complaint) {
        this.vid = vid;
        this.name = name;
        this.aadhar = aadhar;
        this.contact = contact;
        this.password = password;
        this.email = email;
        this.address = address;
        this.state = state;
        this.complaint = complaint;
    }
    public int getVid() {
        return vid;
    }
    public void setVid(int vid) {
        this.vid = vid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAadhar() {
        return aadhar;
    }
    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public List<Complaint> getComplaint() {
        return complaint;
    }
    public void setComplaint(List<Complaint> complaint) {
        this.complaint = complaint;
    }
    @Override
    public String toString() {
        return "Victim [vid=" + vid + ", name=" + name + ", aadhar=" + aadhar + ", contact=" + contact + ", password="
                + password + ", email=" + email + ", address=" + address + ", state=" + state + ", complaint="
                + complaint + "]";
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> data = new ArrayList();

        SimpleGrantedAuthority simpleGrantedAuthority = 
                                        new SimpleGrantedAuthority("victim");
        
        data.add(simpleGrantedAuthority);
        return data;
    }
    @Override
    public String getUsername() {
        return aadhar;
    }

    
}
