package com.example.complaintproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complaintproject.Entity.Victim;

public interface VictimRepo extends JpaRepository<Victim,Integer>
{
    public Victim findVictimByAadhar(String aadhar);
}
