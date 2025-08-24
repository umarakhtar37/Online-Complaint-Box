package com.example.complaintproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Conversation 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int mid;
    String message;
    @ManyToOne(fetch = FetchType.EAGER)
    Complaint complaint;
    
    public Conversation() {
    }
    public Conversation(int mid, String message) {
        this.mid = mid;
        this.message = message;
    }
    public int getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = mid;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Conversation [mid=" + mid + ", message=" + message + "]";
    }
    public Complaint getComplaint() {
        return complaint;
    }
    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
        
}
