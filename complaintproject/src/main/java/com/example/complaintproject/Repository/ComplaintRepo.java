package com.example.complaintproject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complaintproject.Entity.Complaint;

public interface ComplaintRepo extends JpaRepository<Complaint,Integer>
{
    public List<Complaint> findByStatusTrue();    
    public List<Complaint> findByStatusFalse();    
}
