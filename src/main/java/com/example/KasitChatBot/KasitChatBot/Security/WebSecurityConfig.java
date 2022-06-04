package com.example.KasitChatBot.KasitChatBot.Security;

import com.example.KasitChatBot.KasitChatBot.Model.AppUser;
import com.example.KasitChatBot.KasitChatBot.Model.Role;
import com.example.KasitChatBot.KasitChatBot.Repository.RoleRepository;
import com.example.KasitChatBot.KasitChatBot.Repository.UseraRepository;
import com.example.KasitChatBot.KasitChatBot.Security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/signup*").permitAll()
//                .antMatchers("/chatbot*").permitAll()
                .antMatchers("/css/styles.css").permitAll()
                .antMatchers("/css/styleserror.css").permitAll()
                .antMatchers("/css/blog.css").permitAll()
                .antMatchers("/css/chat.css").permitAll()
                .antMatchers("/logo.jpg").permitAll()
                .antMatchers("/faculty.jpg").permitAll()


//                .antMatchers("", "/").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/signup").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/chatbot")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/preform_logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
    }




}
