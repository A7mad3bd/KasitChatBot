package com.example.KasitChatBot.KasitChatBot.Repository;



import com.example.KasitChatBot.KasitChatBot.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Question, Long> {
}