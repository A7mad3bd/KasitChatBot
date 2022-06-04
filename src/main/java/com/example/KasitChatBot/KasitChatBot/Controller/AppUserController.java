package com.example.KasitChatBot.KasitChatBot.Controller;


import com.example.KasitChatBot.KasitChatBot.Model.AppUser;
import com.example.KasitChatBot.KasitChatBot.Model.Question;
import com.example.KasitChatBot.KasitChatBot.Repository.ReviewRepo;
import com.example.KasitChatBot.KasitChatBot.Repository.RoleRepository;
import com.example.KasitChatBot.KasitChatBot.Repository.UseraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class AppUserController {
    RoleRepository roleRepository;
    UseraRepository useraRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    UseraRepository appUseraRepository;

    @GetMapping("/")
    public String HomePage() {
//        roleRepository.save(new Role("ADMIN"));
//        roleRepository.save(new Role("USER"));
//            useraRepository.save(new AppUser("admin", "admin", "admin", "admin", "1/1/1998", "it", roleRepository.findRoleByName("ADMIN")));
        return "login";
    }

    @GetMapping("/chatbot")
    public String bot() {
        return "index";
    }

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login";
//    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }




    @PostMapping("/signup")
    public String signupUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String dateOfBirth,
                             @RequestParam String specialization
//                             @RequestParam Set<Role> role

                             ) {

        AppUser appuser = new AppUser(username, encoder.encode(password), firstName, lastName, dateOfBirth, specialization );
        appUseraRepository.save(appuser);
        return "login";
    }


    @GetMapping("/blog")
    public String comments(Model m, Authentication auth) {
        m.addAttribute("allquestions", reviewRepo.findAll());
        return "blog";

    }

    @GetMapping("/common")
    public String common(Model m, Authentication auth) {
        return "Common";
    }

    @PostMapping("/blog")
    public RedirectView addcomment(Principal p, String body, Model model) {
        AppUser user = (AppUser) appUseraRepository.findByUsername(p.getName());
        LocalDate createdAt = LocalDate.now();
        Question question = new Question(body, user,createdAt);
        reviewRepo.save(question);
        return new RedirectView("/blog");

    }

}

