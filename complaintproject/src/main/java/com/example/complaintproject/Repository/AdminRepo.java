package com.example.complaintproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complaintproject.Entity.Admin;

public interface AdminRepo extends JpaRepository<Admin,Integer>{
    public Admin findAdminByUsername(String username);
}
