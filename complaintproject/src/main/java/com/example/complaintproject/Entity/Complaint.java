package com.example.complaintproject.Entity;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Complaint 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cid;    
    String problem,date;
    @Lob
    byte[] evidance;
    boolean status;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Conversation> conversation;
    @ManyToOne(fetch = FetchType.EAGER)
    Victim victim;
    public Complaint() {
    }
    public Complaint(int cid, String problem, String date, byte[] evidance, boolean status,
            List<Conversation> conversation, Victim victim) {
        this.cid = cid;
        this.problem = problem;
        this.date = date;
        this.evidance = evidance;
        this.status = status;
        this.conversation = conversation;
        this.victim = victim;
    }
    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getProblem() {
        return problem;
    }
    public void setProblem(String problem) {
        this.problem = problem;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public byte[] getEvidance() {
        return evidance;
    }
    public void setEvidance(byte[] evidance) {
        this.evidance = evidance;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public List<Conversation> getConversation() {
        return conversation;
    }
    public void setConversation(List<Conversation> conversation) {
        this.conversation = conversation;
    }
    public Victim getVictim() {
        return victim;
    }
    public void setVictim(Victim victim) {
        this.victim = victim;
    }
    @Override
    public String toString() {
        return "Complaint [cid=" + cid + ", problem=" + problem + ", date=" + date + ", evidance="
                + Arrays.toString(evidance) + ", status=" + status + ", conversation=" + conversation + "]";
    }
    

    
}
