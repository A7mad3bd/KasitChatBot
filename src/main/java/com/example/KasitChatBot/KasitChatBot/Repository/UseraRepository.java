package com.example.KasitChatBot.KasitChatBot.Repository;

import com.example.KasitChatBot.KasitChatBot.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseraRepository extends JpaRepository<AppUser, Long> {
     AppUser findByUsername(String username);


}
