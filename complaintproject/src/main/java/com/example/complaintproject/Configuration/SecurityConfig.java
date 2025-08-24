package com.example.complaintproject.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity  
public class SecurityConfig 
{
    


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        try{
            http.authorizeHttpRequests(auth->{
                auth.requestMatchers("/victim/**").hasAuthority("victim");
                auth.requestMatchers("/admin/**").hasAuthority("admin");
                auth.requestMatchers("/*").permitAll();
                auth.requestMatchers("/website/*").permitAll();
            });
            http.formLogin().permitAll();
            http.csrf().disable();
            return http.build();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

   

    @Bean
    BCryptPasswordEncoder passwordEncoderProvider()
    {
       return new BCryptPasswordEncoder();
    }    
}
