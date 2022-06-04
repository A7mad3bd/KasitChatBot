package com.example.KasitChatBot.KasitChatBot;

import com.example.KasitChatBot.KasitChatBot.Model.Role;
import com.example.KasitChatBot.KasitChatBot.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class KasitChatBotApplication {
	private static final Logger log = LoggerFactory.getLogger(KasitChatBotApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KasitChatBotApplication.class, args);
	}


}
