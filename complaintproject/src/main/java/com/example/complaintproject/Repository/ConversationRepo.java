package com.example.complaintproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.complaintproject.Entity.Conversation;

public interface ConversationRepo extends JpaRepository<Conversation,Integer>
{
    
}
